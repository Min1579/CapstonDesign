package org.devs.heythere_backend.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class UserMypageResponseDto {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String password;
    private String picture;

    @Builder
    public UserMypageResponseDto(Long id, String username, String name, String email, String password, String picture) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.picture = picture;
    }
}
