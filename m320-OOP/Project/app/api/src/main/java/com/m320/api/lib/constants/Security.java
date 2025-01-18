package com.m320.api.lib.constants;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class Security {

    public static final String ALGORITHM = "HmacSHA256";

    public static final String SECRET = "TheUhZJOxhx21hCK2nh5Nk8LIxYUYK1efrDCXn2v8ot7rblTikqFfbvfl1it+W3tB0oMYc7FWEsr8QO1oQc5yA==";

    public static final SecretKeySpec SECRET_KEY_SPEC = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), ALGORITHM);
    public static final long EXPIRATION_TIME = 864_000_000L; // 10 days
    public static String AUTHORIZATION_HEADER_NAME = "Authorization";


}
