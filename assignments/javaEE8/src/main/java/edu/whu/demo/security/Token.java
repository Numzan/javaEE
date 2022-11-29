package edu.whu.demo.security;
import edu.whu.demo.entity.user;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Token {

    public static final long JWT_TOKEN_VALIDITY = 5*60*60*1000;

    @Value("${jwt.secret}")
    private String secret;
    public Claims getClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token).getBody();
    }
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    public boolean validateClaim(Claims claim,UserDetails userDetails) {
        //可以验证其他信息，如角色
        return userDetails != null &&
                claim.getSubject().equals(userDetails.getUsername())
                && !claim.getExpiration().before(new Date());
    }

    public Boolean canTokenBeRefreshed(String token) {
        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public Boolean isTokenExpired(String token) {
        Claims claim = getClaimFromToken(token);
        return claim.getExpiration().before(new Date());
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }
}
