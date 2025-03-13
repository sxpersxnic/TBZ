package com.github.sxpersxnic.tbz.m320.config;

import com.github.sxpersxnic.tbz.m320.lib.constants.Security;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.nio.file.AccessDeniedException;
import java.util.List;

/// Security configuration class
///
/// This class is responsible for configuring the security of the application.
///
/// It defines the *security filter chain*, the *CORS configuration*, the *JWT decoder*, the *authentication manager* and the *password encoder*.
///
/// @author sxpersxnic
@Configuration
@SecurityScheme(type = SecuritySchemeType.HTTP,
        name = Security.AUTHORIZATION_HEADER_NAME,
        in = SecuritySchemeIn.HEADER,
        bearerFormat = "JWT",
        scheme = "bearer")
@EnableWebSecurity
@EnableMethodSecurity(proxyTargetClass = true)
public class SecurityConfiguration {

    /// Returns a new instance of the {@link BCryptPasswordEncoder}.
    ///
    /// Used to Hash passwords.
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /// Returns a new instance of the {@link SecurityFilterChain}.
    /// Configures the security filter chain.
    /// The security filter chain allows all POST requests to the authentication URLs and all GET requests to the public URLs.
    ///
    /// @see Security#AUTH_URLS
    /// @see Security#PUBLIC_URLS
    @Bean
    public SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {
        try {
            http.csrf(CsrfConfigurer::disable)
                    .cors(customizer -> customizer.configurationSource(corsConfigurationSource()))
                    .authorizeHttpRequests(authorizeRequest ->
                            authorizeRequest
                                    .requestMatchers(HttpMethod.POST, Security.AUTH_URLS).permitAll()
                                    .requestMatchers(HttpMethod.GET, Security.PUBLIC_URLS).permitAll()
                                    .anyRequest().authenticated()
                    )
                    .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()))
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            return http.build();
        } catch (Exception ex) {
            throw new AccessDeniedException("Access denied");
        }
    }

    /// Returns a new instance of the {@link CorsConfigurationSource}.
    ///
    /// Configures the *CORS* configuration (**C**ross-**O**rigin **R**esource **S**haring).
    ///
    /// For development purposes, it allows all origins, methods and headers.
    ///
    /// In production, it should be configured to allow only the necessary origins, methods and headers.
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /// Returns a new instance of the {@link AuthenticationManager}.
    ///
    /// The authentication manager is used to authenticate the user.
    ///
    /// @param authenticationConfiguration The authentication configuration.
    /// @return The authentication manager.
    /// @exception Exception If an error occurs while creating the authentication manager.
    ///
    /// @see AuthenticationConfiguration#getAuthenticationManager()
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /// Returns a new instance of the {@link JwtDecoder}.
    ///
    /// The JWT decoder is used to decode the JWT token.
    ///
    /// @return The JWT decoder.
    ///
    /// @see Security#SECRET_KEY_SPEC
    /// @see NimbusJwtDecoder#withSecretKey
    @Bean
    public JwtDecoder customDecoder() {
        return NimbusJwtDecoder
                .withSecretKey(Security.SECRET_KEY_SPEC)
                .build();
    }

}
