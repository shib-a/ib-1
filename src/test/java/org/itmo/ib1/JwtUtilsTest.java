package org.itmo.ib1;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = JwtService.class)
public class JwtUtilsTest {
    @Test
    public void parseShouldParseToken(){
//        String token = JwtService.createToken();
//        Claims claims = JwtService.parseTokenClaims(token);
//        assertNotNull(claims);
    }
}
