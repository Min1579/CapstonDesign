package org.devs.heythere_backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findById(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    List<User> findByUsernameContainingOrNameContainingOrEmailContaining(String username, String name, String email);

    @Query("select u from User u where u.status=?1")
    List<User> findByUserStatusIsOn(final UserStatus status);
}
