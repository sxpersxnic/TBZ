package com.github.sxpersxnic.tbz.m320.lib.constants;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/// Security constants.
/// @author sxpersxnic
public class Security {
    /// The secret key used to sign the JWT.
    /// @see Security#ALGORITHM
    public static final String SECRET = "256-BIT-SECRET-KEY";
    /// The algorithm used to sign the JWT.
    /// @see Security#SECRET
    public static final String ALGORITHM = "HmacSHA256";
    /// The secret key spec used to sign the JWT.
    ///
    /// Uses UTF-8 encoding.
    /// @see Security#SECRET
    /// @see Security#ALGORITHM
    public static final SecretKeySpec SECRET_KEY_SPEC = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), ALGORITHM);

    /// The expiration time of the JWT.
    ///
    /// Notated in milliseconds.
    ///
    /// 864_000_000L = 10 days
    ///
    /// **Note:** Only in development mode the expiration time should be set to 10 days.
    ///
    /// In production mode the expiration time should be set to 15 minutes and use refresh tokens.
    public static final long EXPIRATION_TIME = 864_000_000L;

    /// The URLs used for authentication.
    public static final String AUTH_URLS = "/auth/**";

    /// The public URLs, which do not require authentication.
    public static final String[] PUBLIC_URLS = {
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/",
            "/seed",
    };

    /// The name of the authorization header.
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";

}
