package com.github.sxpersxnic.tbz.m320.lib.jwt;

import com.github.sxpersxnic.tbz.m320.lib.constants.Security;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author sxpersxnic
 */
public final class JwtGenerator {

    public static String generateJwtToken(Authentication authentication) {
        try {
            SignedJWT jwt = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), buildJWTClaimsSet(authentication));
            jwt.sign(new MACSigner(Security.SECRET_KEY_SPEC));
            return jwt.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private static JWTClaimsSet buildJWTClaimsSet(Authentication authentication) {
        return new JWTClaimsSet.Builder()
                .subject(authentication.getName())
                .claim("scope", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" ")))
                .expirationTime(new Date(System.currentTimeMillis() + Security.EXPIRATION_TIME))
                .build();
    }

}
