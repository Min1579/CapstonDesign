package org.devs.heythere_backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("search/{usernameOrNameOrEmail}")
    public ResponseEntity<?> searchByUsernameOrNameOrEmail(@PathVariable String usernameOrNameOrEmail){
        List<UserResearchFoundResponseDto> users = userService.searchByUsernameOrNameOrEmail(usernameOrNameOrEmail);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("mypage")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> uploadProfilePicture() {
        return ResponseEntity.ok("mypage will implement soon!");
    }



}
