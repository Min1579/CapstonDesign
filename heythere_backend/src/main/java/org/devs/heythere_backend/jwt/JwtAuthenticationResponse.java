package org.devs.heythere_backend.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class JwtAuthenticationResponse {
    private static final String TOKEN_TYPE = "Bearer";
    private final String accessToken;
}