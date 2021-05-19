package com.example.springdemo.security;

import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Date;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenUtilTest {
    
    private static final byte[] sharedKey = new byte[32];
    
    @BeforeAll
    public static void beforeAll() {
        var random = new SecureRandom();
        random.nextBytes(sharedKey);
    }

    @Test
    void generateToken() {
        var target = new JwtTokenUtil(sharedKey);
        String userId = "1234567890";
        var claims = new JWTClaimsSet.Builder()
            .jwtID(userId)
            .claim("userId", userId)
            .issuer("/spring-demo")
//            .issueTime(new Date())
            .expirationTime(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .build();
        System.out.println(claims.toString());
        var jwt = target.generateToken(claims);
        assertNotNull(jwt);
        System.out.println(jwt);
        var parsedClaims = target.parseToken(jwt);
        assertNotNull(parsedClaims);
        System.out.println(parsedClaims.toString());
        assertEquals(userId, parsedClaims.getJWTID());
    }
}
