package org.itmo.ib1;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public final class SecurityUser implements UserDetails {
    private final Long userId;
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    private SecurityUser(Long id, String username, String password, List<GrantedAuthority> authorities) {
        this.userId = id;
        this.username = username;
        this.password = password;
        this.authorities = List.copyOf(authorities);
    }

    public static SecurityUser from(ApplicationUser user) {
        return new SecurityUser(user.getId(), user.getUsername(), user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
