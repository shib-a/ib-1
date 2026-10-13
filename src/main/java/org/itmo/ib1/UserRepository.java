package org.itmo.ib1;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findApplicationUserByUsername(String username);

    boolean existsByUsername(String username);
}
