package com.train.notice.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;
    private final long expiration;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expiration){
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiration= expiration;
    }


    public String createToken(UserDetails userdetails){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(userdetails.getUsername())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    public Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token){
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
