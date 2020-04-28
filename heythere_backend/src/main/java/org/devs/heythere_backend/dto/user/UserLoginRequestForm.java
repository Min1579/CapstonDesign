package org.devs.heythere_backend.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter @Getter
@RequiredArgsConstructor
public class UserLoginRequestForm {
    private final String usernameOrEmail;
    private final String password;
}
