package org.devs.heythere_backend.user;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class UserOnStreamingResponseDto {
    private Long id;
    private String username;
    private String name;
    private String picture;

    @Builder
    public UserOnStreamingResponseDto(Long id, String username, String name, String picture) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.picture = picture;
    }
}
