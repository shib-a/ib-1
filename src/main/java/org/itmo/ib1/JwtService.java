package org.itmo.ib1;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Component
public class JwtService {
    private final SecretKey signingKey;

    private JwtService(@Value("${jwt.signing.key}") String jwtSecret) {
        this.signingKey = new SecretKeySpec(jwtSecret.getBytes(),
                Jwts.SIG.HS256.getId());
    }

    public String createToken(Map<String, Object> claims, String subject) {
        Instant now = Instant.now();
        return Jwts.builder().claims()
                .add(claims)
                .subject(subject)
                .issuer("TestApplication")
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(10, ChronoUnit.MINUTES)))
                .and()
                .signWith(signingKey)
                .compact();
    }
    public Jws<Claims> parseToken(String token){
        return Jwts.parser().decryptWith(signingKey).build().parseSignedClaims(token);
    }

    public Claims parseTokenClaims(String token){
        return Jwts.parser().decryptWith(signingKey).build().parseSignedClaims(token).getPayload();
    }
}
