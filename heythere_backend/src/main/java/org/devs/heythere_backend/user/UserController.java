package org.devs.heythere_backend.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;
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
    public ResponseEntity<?> searchByUsernameOrNameOrEmail(@PathVariable String usernameOrNameOrEmail){
        List<UserResearchFoundResponseDto> users = userService.searchByUsernameOrNameOrEmail(usernameOrNameOrEmail);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("mypage/{serId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserMypageResponseDto> findUserByIdAndSendToMypage(@PathVariable("userId") final Long userId) {
        final UserMypageResponseDto response = userService.findUserByIdAndSendToMypage(userId);
        log.info("mypage");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("mypage/{serId}/edit-profile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> editPicture(@PathVariable final Long serId, @RequestBody final UserProfileEditForm form) throws NameNotFoundException {
        final Long userEditId = userService.editProfile(serId, form);
        return new ResponseEntity<>(userEditId, HttpStatus.OK);
    }

}
