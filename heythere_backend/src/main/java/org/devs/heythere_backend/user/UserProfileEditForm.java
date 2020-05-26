package org.devs.heythere_backend.user;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class UserProfileEditForm {
    private String email;
    private String name;
    private String password;

    @Builder
    public UserProfileEditForm(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
