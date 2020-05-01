package org.devs.heythere_backend.user;

import lombok.*;
import org.devs.heythere_backend.user.User;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestForm {
    private String username;
    private String name;
    private String email;
    private String password;

    public User toUserEntity() {
        return User.builder()
                .username(username)
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
