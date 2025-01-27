package com.github.sxpersxnic.tbz.m320.lib.constants;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author sxpersxnic
 */
public class Security {

    public static final String SECRET = "88cb30d4b5f661f4a1d1da585f21d8ca70fb886099b8176ef4a091300462bf86c572c2e0b624a5a84a866a85af00b5daa3d4aad4c7dcb4d9ebdf66a935026b6e6dd12584ae3406932776c3c03c691d98e75bfe53344d41574204d371a5fbb6d54d78c748aaf9063dcdfa5c6abbf1e9d6197c249bf73b4021d471902c38dd9e51cd2f509e7d3cebff770289592e411a3db0e9efa8938472f66bec43285ebcfc500f19b0d5d724de48f922317e625919be2b76168526b4778f7c2449aa5fb3dfb7a019b511009158fe6056f7481e469bdf889d45c157640230c61549d13415cab71840133f85765a805f30b0f32083bd19e4c7aec6fb10d1a60fb8ea8c3b4a838e";

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
