package org.devs.heythere_backend.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserRegisterFormRequestDto {
    private String email;
    private String username;
    private String name;
    private String pwd;
    private String introduction;

    @Builder
    public UserRegisterFormRequestDto(String email, String username, String name, String pwd, String introduction) {
        this.email = email;
        this.username = username;
        this.name = name;
        this.pwd = pwd;
        this.introduction = introduction;
    }
}
