package org.devs.heythere_backend.system;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserStatusOnUserListResponseDto {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String picture;

    @Builder
    public UserStatusOnUserListResponseDto(Long id, String username, String name, String email,  String picture) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
}
