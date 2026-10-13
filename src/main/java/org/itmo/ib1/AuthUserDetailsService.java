package org.itmo.ib1;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public AuthUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username){
        ApplicationUser user = userRepository.findApplicationUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found"));
        return SecurityUser.from(user);
    }
}
