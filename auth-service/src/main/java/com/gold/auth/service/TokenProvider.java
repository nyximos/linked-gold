package com.gold.auth.service;


import com.gold.auth.core.util.TokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class TokenProvider {

    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.prefix}")
    private String tokenPrefix;

    @Value("${jwt.access.expire}")
    private long jwtExpireDuration;

    private SecretKey getSecretKey() {
        return TokenUtils.getSecretKey(key);
    }

    public String issueToken(Map<String, Object> tokenInfo) {
        long current = System.currentTimeMillis();
        return tokenPrefix + StringUtils.SPACE +
                Jwts.builder()
                        .subject((String) tokenInfo.get("username"))
                        .claims(tokenInfo)
                        .issuedAt(new Date(current))
                        .expiration(new Date(current + jwtExpireDuration))
                        .signWith(getSecretKey())
                        .compact();
    }

    public Claims parseJwt(String token) {
        return TokenUtils.extractAllClaims(TokenUtils.getSecretKey(key),token);
    }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = parseJwt(token);
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String removePrefix(String token) {
        if (token.startsWith(tokenPrefix + StringUtils.SPACE)) {
            return token.substring(tokenPrefix.length() + 1);
        }
        return token;
    }

}