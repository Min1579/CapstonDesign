package org.devs.heythere_backend.jwt;

import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@Builder
@ToString
public class JwtAuthenticationResponse {
    private final String userId;
    private final String username;
    private final String name;
    private final String email;
    private final String picture;
    private static final String TOKEN_TYPE = "Bearer";
    private final String accessToken;
}