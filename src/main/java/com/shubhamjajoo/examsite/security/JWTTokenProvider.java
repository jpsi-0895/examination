package com.shubhamjajoo.examsite.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpiration;

    public String generateToken(Authentication authentication) {
        String email = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(jwtToken);
            return true;
        } catch (SignatureException signatureException) {
            throw new RuntimeException("Invalid JWT signature");
        } catch (MalformedJwtException malformedJwtException) {
            throw new RuntimeException("Invalid JWT token");
        } catch (ExpiredJwtException expiredJwtException) {
            throw new RuntimeException("Expired JWT token");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            throw new RuntimeException("Unsupported JWT token");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new RuntimeException("JWT claims string is empty");
        }
    }

    public String getEmailFromToken (String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
    }

}
