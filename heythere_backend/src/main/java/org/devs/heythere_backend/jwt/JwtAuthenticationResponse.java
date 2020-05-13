package org.devs.heythere_backend.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@Builder
public class JwtAuthenticationResponse {
    private final Long userId;
    private final String username;
    private final String name;
    private final String email;
    private final String picture;
    private static final String TOKEN_TYPE = "Bearer";
    private final String accessToken;
}