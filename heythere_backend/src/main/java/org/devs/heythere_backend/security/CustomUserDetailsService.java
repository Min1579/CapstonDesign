package org.devs.heythere_backend.security;

import lombok.RequiredArgsConstructor;
import org.devs.heythere_backend.user.User;
import org.devs.heythere_backend.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        final User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("USER NOT FOUND WITH INPUT INFO :" + usernameOrEmail));

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(final Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UsernameNotFoundException("USER NOT FOUND WITH ID" + id));

        return UserPrincipal.create(user);
    }
}
