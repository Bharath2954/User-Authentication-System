package com.UAuth.UserAuthSystem.config;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtService {

    private static final String SECRET_KEY = "n3DxkOAuMRtM0m1jsvcOA2ort8xbBwU8VRBwm2f5sPZiuShjDS303xG5MzAB4vIj";

    public String extractUserName(String jwt)
    {
        return extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver)
    {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails)
    {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (24L * 60 * 60 * 1000)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String userName = extractUserName(jwt);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(jwt);
    }

    public String expireToken(String jwt) {
        try {
            Claims claims = extractAllClaims(jwt);
            claims.setExpiration(new Date(System.currentTimeMillis()));
            return Jwts.builder()
                    .setClaims(claims)
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
        }
        catch (Exception e) {
            e.printStackTrace();
            return jwt;
        }
    }

    private boolean isTokenExpired(String jwt)
    {
        return extractExpiration(jwt).before(new Date(System.currentTimeMillis()));
    }

    private Date extractExpiration(String jwt)
    {
        return extractClaim(jwt, Claims::getExpiration);
    }

    private Claims extractAllClaims(String jwt)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private Key getSignInKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}


