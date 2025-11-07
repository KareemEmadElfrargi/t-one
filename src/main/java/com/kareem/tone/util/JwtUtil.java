package com.kareem.tone.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "yourSuperSecretKeyWhichIsAtLeast32CharsLongForHS256";

    public String generateToken(String email) {
        long oneYearInMillis = 1000L * 60 * 60 * 24 * 365;

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + oneYearInMillis))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // ✅ أضف الخوارزمية هنا
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
