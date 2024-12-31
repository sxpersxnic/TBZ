package com.cestra.api.lib.constants;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class SecurityConstants {

    public static final String CONTEXT_PATH = "/";

    public static final String CLIENT_URL = "http://localhost:3000";

    public static final String SECRET = "dev";

    public static final String ALGORITHM = "HmacSHA256";

    public static final SecretKeySpec SECRET_KEY_SPEC = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), ALGORITHM);

    public static final long EXPIRATION_TIME = 864_000_000L; // 10 days

    public static final String AUTH_URLS = "/auth/**";

    public static final String[] PUBLIC_URLS = {
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/**"
    };

    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";

}
