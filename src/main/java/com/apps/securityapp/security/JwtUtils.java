package com.apps.securityapp.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String parseAuthorizationHeader(HttpServletRequest request){
        if(request.getHeader("Authorization").startsWith("Bearer")) {
            return request.getHeader("Authorization").substring("Bearer".length()).trim();
        } else {
            return null;
        }
    }

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().verifyWith(getKey()).build().parse(token);
            return true;
        } catch (MalformedJwtException e) {
            LOG.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOG.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOG.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOG.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token)
                .getPayload().getSubject();
    }

    public String generateJwtToken(Authentication authentication){
        return Jwts.builder().signWith(getKey())
                .subject(((UserDetails) authentication.getPrincipal()).getUsername())
                .expiration(new Date((new Date()).getTime() + expiration))
                .issuedAt(new Date())
                .compact();
    }
}
