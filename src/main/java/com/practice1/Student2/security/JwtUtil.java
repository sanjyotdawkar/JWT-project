package com.practice1.Student2.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {


    // must be at least 32 characters. In a real project, keep this in an
    // environment variable or application.properties, not in code.
    private final SecretKey key =
            Keys.hmacShaKeyFor("my-secret-key-for-jwt-minimum-32-chars".getBytes());

    // makes a token for this username, valid for 1 hour
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

    // reads the username from a token (throws an exception if fake or expired)
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
