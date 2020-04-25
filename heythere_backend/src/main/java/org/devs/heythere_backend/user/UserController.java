package org.devs.heythere_backend.user;

import lombok.RequiredArgsConstructor;
import org.devs.heythere_backend.dto.user.UserRegisterRequestForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequestForm registerRequestForm) {
        Long userId = userService.register(registerRequestForm.toUserEntity());
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @GetMapping("test")
    @PreAuthorize("hasRole('USER')")
    public String test() {
        return "asdfsaf";
    }

    @GetMapping("admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "admin Page";
    }
}
