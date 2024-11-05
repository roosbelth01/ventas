package com.ralva.ventas.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    public String generateToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        List<String> roles = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + 1000 * 60 * 60 * 24);

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .claims(claims)
                .issuedAt(currentDate)
                .expiration(expirationDate)
                .signWith(getKek())
                .compact();
    }

    private Key getKek() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(Jwts.parser()
                .verifyWith((SecretKey) getKek())
                .build()
                .parseSignedClaims(token)
                .getPayload());
    }

    public String getUsernameFromJWT(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) getKek()).build().parseSignedClaims(token).getPayload();
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty", e);
        } //catch (SignatureException e) {
            //logger.error("Invalid JWT signature", e);
        //}
        return false;
    }

    public String refreshToken(Authentication authentication) {
        try {
            UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
            List<String> roles = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            Date currentDate = new Date();
            Date expirationDate = new Date(currentDate.getTime() + 1000 * 60 * 60 * 24);
            Map<String, Object> claims = new HashMap<>();
            claims.put("roles", roles);

            return Jwts.builder()
                    .subject(userPrincipal.getUsername())
                    .claims(claims)
                    .issuedAt(currentDate)
                    .expiration(expirationDate)
                    .signWith(getKek())
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
