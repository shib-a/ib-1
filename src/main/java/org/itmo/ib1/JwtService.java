package org.itmo.ib1;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
public final class JwtService {
    private static final String ISSUER = "ib-1";

    private final SecretKey signingKey;
    private final Duration ttl;

    public JwtService(@Value("${jwt.signing.key}") String jwtSecret, @Value("${jwt.ttl:10m}") Duration ttl) {
        this.signingKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        this.ttl = ttl;
    }

    public String createToken(Map<String, Object> claims, String subject) {
        Instant now = Instant.now();
        return Jwts.builder().claims()
                .add(claims)
                .subject(subject)
                .issuer(ISSUER)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(ttl)))
                .and()
                .signWith(signingKey, Jwts.SIG.HS256)
                .compact();
    }

    public Jws<Claims> parseToken(String token){
        return Jwts.parser().verifyWith(signingKey).requireIssuer(ISSUER).build().parseSignedClaims(token);
    }

    public Claims parseTokenClaims(String token){
        return parseToken(token).getPayload();
    }
}
