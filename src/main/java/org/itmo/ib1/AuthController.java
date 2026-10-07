package org.itmo.ib1;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AuthController {
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        
    }
}
