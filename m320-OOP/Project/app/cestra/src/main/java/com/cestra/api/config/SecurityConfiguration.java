package com.cestra.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Value("${cestora.auth.urls}")
    private static final String AUTH_URLS;

    @Value("${cestora.root.path}")
    private static final String ROOT_PATH;

    @Value("${cestora.api.documentation.urls}")
    private static final String API_DOCUMENTATION_URLS;

    @Value("${cestora.ws.endpoint}")
    private static final String WS_ENDPOINT;

    @Value("${cestora.secret.key.spec}")
    private static final String SECRET_KEY_SPEC;

    @Value("${cestora.jwt.issuer}")
    private static final String JWT_ISSUER;

    @Value("${cestora.jwt.audience}")
    private static final String JWT_AUDIENCE;

    @Value("${cestora.jwt.expiration}")
    private static final String JWT_EXPIRATION;

    @Value("${cestora.jwt.claim.roles}")
    private static final String JWT_CLAIM_ROLES;

    @Value("${cestora.jwt.claim.permissions}")
    private static final String JWT_CLAIM_PERMISSIONS;

    @Value("${cestora.jwt.claim.username}") 
    private static final String JWT_CLAIM_USERNAME; 

    @Value("${cestora.jwt.claim.email}")    

    private static final String JWT_CLAIM_EMAIL;

    @Value("${cestora.jwt.claim.fullname}")
    private static final String JWT_CLAIM_FULLNAME;

    @Value("${server.servlet.context-path}")
    private static final String CONTEXT_PATH;

    @Value("${cestora.profile}")
    private static final String PROFILE;

    if 

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable)
                .cors(customizer -> customizer.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest
                                .requestMatchers(HttpMethod.POST, SecurityConstants.AUTH_URLS).permitAll()
                                .requestMatchers(HttpMethod.GET, SecurityConstants.ROOT_PATH).permitAll()
                                .requestMatchers(HttpMethod.GET, SecurityConstants.API_DOCUMENTATION_URLS).permitAll()

                                .requestMatchers(new AntPathRequestMatcher(CONTEXT_PATH + "/**")).permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:9000"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtDecoder customDecoder() {
        return NimbusJwtDecoder
                .withSecretKey(SecurityConstants.SECRET_KEY_SPEC)
                .build();
    }

}
}
