package org.devs.heythere_backend.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@Slf4j
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

    @PostMapping("search/{usernameOrNameOrEmail}")
    public ResponseEntity<?> searchByUsernameOrNameOrEmail(@PathVariable final String usernameOrNameOrEmail){
        List<UserResearchFoundResponseDto> users = userService.searchByUsernameOrNameOrEmail(usernameOrNameOrEmail);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole('USER')")
    @GetMapping("mypage/{userId}")
    public ResponseEntity<UserMypageResponseDto> findUserByIdAndSendToMypage(@PathVariable("userId") final Long userId) {
        final UserMypageResponseDto response = userService.findUserByIdAndSendToMypage(userId);
        log.info("mypage");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
