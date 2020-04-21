package org.devs.heythere_backend.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return userRepository.save(
                grantDefaultUserRoleAndEncodePassword(user)
        ).getId();
    }

    private User grantDefaultUserRoleAndEncodePassword(final User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRole(RoleType.ROLE_USER));

        return User.builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .picture(user.getPicture())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(roles).build();
    }

}
