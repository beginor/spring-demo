package com.example.springdemo.security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {
    
    private byte[] sharedKey;

    public JwtTokenUtil(byte[] sharedKey) {
        this.sharedKey = sharedKey;
    }

    public @Nullable String generateToken(@NonNull JWTClaimsSet claims) {
        try {
            var header = new JWSHeader(JWSAlgorithm.HS256);
            var payload = new Payload(claims.toJSONObject(false));
            var jwsObject = new JWSObject(header, payload);
            jwsObject.sign(new MACSigner(sharedKey));
            return jwsObject.serialize();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public @Nullable JWTClaimsSet parseToken(@NonNull String token) {
        try {
            var jwsObject = JWSObject.parse(token);
            var verifier = new MACVerifier(sharedKey);
            return jwsObject.verify(verifier) ? JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject()) : null;
        }
        catch (Exception ex) {
            return null;
        }
    }
}
