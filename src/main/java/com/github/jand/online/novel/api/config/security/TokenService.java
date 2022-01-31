package com.github.jand.online.novel.api.config.security;

import com.github.jand.online.novel.api.model.User;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${base.api.jwt.expiration}")
    private String expiration;

    @Value("${base.api.jwt.secret}")
    private String secret;

    public String createToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("Online-Novel")
                .setSubject(user.getId().toString())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUser(String token) {
        return Long.parseLong(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject());
    }
}
