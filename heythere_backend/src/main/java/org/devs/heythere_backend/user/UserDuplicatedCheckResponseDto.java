package org.devs.heythere_backend.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDuplicatedCheckResponseDto {
    private Boolean valid;

    public UserDuplicatedCheckResponseDto(Boolean valid) {
        this.valid = valid;
    }
}
