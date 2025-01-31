package com.github.sxpersxnic.tbz.m320.lib.constants;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author sxpersxnic
 */
public class Security {

    public static final String SECRET = "256-BIZ-SECRET";

    public static final String ALGORITHM = "HmacSHA256";

    public static final SecretKeySpec SECRET_KEY_SPEC = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), ALGORITHM);

    public static final long EXPIRATION_TIME = 864_000_000L; // 10 days

    public static final String AUTH_URLS = "/auth/**";

    public static final String[] PUBLIC_URLS = {
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/",
            "/seed",
    };

    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";

}
