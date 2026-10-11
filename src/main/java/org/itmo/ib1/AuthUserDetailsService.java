package org.itmo.ib1;

import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService {
    private final UserRepository userRepository;
    public AuthUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
}
