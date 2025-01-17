package com.m320.api.lib.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.util.Date;
import java.util.UUID;

import static com.m320.api.lib.constants.Security.EXPIRATION_TIME;
import static com.m320.api.lib.constants.Security.SECRET_KEY_SPEC;

public final class JwtGenerator {

    /**
     * Generates a JWT token for the given id.
     *
     * @param id the id for which to generate the JWT token
     * @return the generated JWT token
     */
    public static String generateJwtToken(UUID id) throws JOSEException {
        try {
            SignedJWT jwt = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), buildJWTClaimSet(id));
            jwt.sign(new MACSigner(SECRET_KEY_SPEC));
            return jwt.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Builds a JWTClaimsSet object for the given id.
     *
     * @param id the id for which to build the JWTClaimsSet
     * @return the constructed JWTClaimsSet object
     */
    private static JWTClaimsSet buildJWTClaimSet(UUID id) {
        return new JWTClaimsSet.Builder()
                .subject(id.toString())
                .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .build();
    }
}
