package com.example.vehiclefleetmanagement.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private String jwtSecretKeys = "qwerty";
    private Integer experiationTime = 5000000;

    public String generateToken(Authentication authentication) {
        UserDetailsImplementation user = (UserDetailsImplementation) authentication.getPrincipal();
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(new Date(new Date().getTime() + experiationTime))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, jwtSecretKeys)
                .compact();
        return token;
    }

    public String getUserNameFromToken(String token) {
        String userName = Jwts.parser()
                .setSigningKey(jwtSecretKeys)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userName;
    }

    public Boolean validate(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecretKeys)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
