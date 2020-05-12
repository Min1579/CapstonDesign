package org.devs.heythere_backend.security;

import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;

    @PostMapping("signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginRequestForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        final String token = tokenProvider.generateToken(authentication);
        Long userId = tokenProvider.getUserIdFromJwt(token);
        final User loginUser = userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("USER NOT FOUND")
        );

        final JwtAuthenticationResponse response = JwtAuthenticationResponse.builder()
                .userId(userId)
                .username(loginUser.getUsername())
                .name(loginUser.getName())
                .email(loginUser.getEmail())
                .accessToken(token)
                .build();

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
