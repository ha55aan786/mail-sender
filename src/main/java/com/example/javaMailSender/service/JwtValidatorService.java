package com.example.javaMailSender.service;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.security.interfaces.RSAPublicKey;

@Service
public class JwtValidatorService {

    @Value("${REG_SERVICE_JWKS}")
    private String regJwksUrl;

    JwtValidatorService() {

    }
    public boolean validateToken(String token) throws Exception {
        try {
            // Parse the JWT
            SignedJWT signedJWT = SignedJWT.parse(token);
            String kid = signedJWT.getHeader().getKeyID();

            // Fetch JWKS
            JWKSet jwkSet = JWKSet.load(new URL(regJwksUrl));
            JWK jwk = jwkSet.getKeyByKeyId(kid);

            if (jwk == null) {
                throw new RuntimeException("No matching key found in JWKS");
            }

            // Convert to RSA public key
            RSAKey rsaKey = (RSAKey) jwk;
            RSAPublicKey publicKey = rsaKey.toRSAPublicKey();

            // Verify signature
            return signedJWT.verify(new com.nimbusds.jose.crypto.RSASSAVerifier(publicKey));
        } catch (Exception e) {
            throw new Exception (e);
        }
    }

}

