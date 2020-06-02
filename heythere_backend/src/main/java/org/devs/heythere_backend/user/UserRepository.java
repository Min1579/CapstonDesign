package org.devs.heythere_backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(final String username, final String email);
    Optional<User> findById(final Long id);
    boolean existsByUsername(final String username);
    boolean existsByEmail(final String email);
    boolean existsByName(final String name);

    List<User> findByUsernameContainingOrNameContainingOrEmailContaining(final String username, final String name,final String email);

    @Query("select u from User u where u.status=?1")
    List<User> findByUserStatusIsOn(final UserStatus status);
}
