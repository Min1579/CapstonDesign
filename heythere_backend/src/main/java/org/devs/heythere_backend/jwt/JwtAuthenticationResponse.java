package org.devs.heythere_backend.jwt;

import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@Builder
public class JwtAuthenticationResponse {
    private final String userId;
    private static final String TOKEN_TYPE = "Bearer";
    private final String accessToken;
}