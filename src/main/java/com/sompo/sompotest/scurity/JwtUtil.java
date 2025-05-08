package com.sompo.sompotest.scurity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationMillis;

    public String generateToken(UserDetails user) {
        Algorithm algo = Algorithm.HMAC256(secret);
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationMillis))
                .sign(algo);
    }

    public DecodedJWT validateToken(String token) {
        Algorithm algo = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algo).build();
        return verifier.verify(token);
    }

    public String getUsername(String token) {
        return validateToken(token).getSubject();
    }

    public boolean isExpired(String token) {
        return validateToken(token).getExpiresAt().before(new Date());
    }
}
