package com.example.userservice.security;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey key;

    @PostConstruct
    public void init(){
        key= Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(String email){
        Date now = new Date();

        Date expiry= new Date(now.getTime()+ jwtExpiration);

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(key)
                .compact();
    }

    public String getEmailFromToken(String jwtToken){
        Claims claims= Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();

        return claims.getSubject();
    }

    public boolean validateJwt(String jwtToken){
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(jwtToken);
            return true;

        } catch (ExpiredJwtException ex) {
            log.error("JWT expired {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims empty {}", ex.getMessage());
        } catch (Exception ex){
            log.error("Runtime Exception {}", ex.getMessage());
        }
        return false;
    }
}
