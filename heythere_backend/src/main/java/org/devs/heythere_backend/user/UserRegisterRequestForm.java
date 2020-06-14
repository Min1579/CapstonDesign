package org.devs.heythere_backend.user;

import lombok.*;
import org.devs.heythere_backend.user.User;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter @Getter
@RequiredArgsConstructor
public class UserRegisterRequestForm {
    @NotBlank
    private final String username;
    @NotBlank
    private final String name;
    @NotBlank
    private final String email;
    @NotBlank
    private final String password;

    public User toUserEntity() {
        return User.builder()
                .username(username)
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
