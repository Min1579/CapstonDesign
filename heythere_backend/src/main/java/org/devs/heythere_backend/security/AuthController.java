package org.devs.heythere_backend.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devs.heythere_backend.jwt.JwtAuthenticationResponse;
import org.devs.heythere_backend.user.User;
import org.devs.heythere_backend.user.UserLoginRequestForm;
import org.devs.heythere_backend.jwt.JwtTokenProvider;
import org.devs.heythere_backend.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;

    @PostMapping("api/auth/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginRequestForm loginRequest) {
        log.info("auth request come!");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        final String token = tokenProvider.generateToken(authentication);
        Long userId = tokenProvider.getUserIdFromJwt(token);
        final User loginUser = userRepository.findById(userId)
                .orElseThrow(
                        () -> new UsernameNotFoundException("USER NOT FOUND")
        );

        final JwtAuthenticationResponse response = JwtAuthenticationResponse.builder()
                .userId(String.valueOf(userId))
                .accessToken(token)
                .build();

        log.info("auth user : {} " , response);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

    }
}
