package org.devs.heythere_backend.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Transactional
    public boolean existByUsername(final String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public boolean existByEmail(final String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public Long register( final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole(RoleName.ROLE_USER);
        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user).getId();
    }

}
