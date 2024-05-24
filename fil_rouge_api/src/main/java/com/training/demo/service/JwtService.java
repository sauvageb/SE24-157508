package com.training.demo.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String JWT_SECRET = "passwordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpassword";
    private static final long EXPIRATION_DATE = 864_000_000;

    public String generateToken(Map<String, Object> extraClaims, String username) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
                .signWith(getSigninKey())
                .compact();
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName());
    }

    public boolean isTokenValid(String headerToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(headerToken);
            return true;
        } catch (SignatureException e) {
            throw new AuthenticationCredentialsNotFoundException("Invalid JWT signature: {} " + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("Invalid JWT token: {} " + e.getMessage());
        } catch (ExpiredJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("Invalid JWT expired: {} " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("JWT token is not unsupported : {} " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new AuthenticationCredentialsNotFoundException("JWT claims string is empty: {} " + e.getMessage());
        }
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
    }


}
