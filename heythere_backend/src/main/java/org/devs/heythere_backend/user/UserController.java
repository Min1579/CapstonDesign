package org.devs.heythere_backend.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @PostMapping("mypage/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserMypageResponseDto> findUserByIdAndSendToMypage(@PathVariable("userId") final Long userId) {
        final UserMypageResponseDto response = userService.findUserByIdAndSendToMypage(userId);
        log.info("mypage");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("mypage/{userId}/edit-profile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> editProfile(@PathVariable final Long userId,
                                         @RequestBody final UserProfileEditForm form
                                        ) {
        Long userEditId = userService.editProfile(userId , form);
        return new ResponseEntity<>(userEditId, HttpStatus.OK);
    }
    @PutMapping("mypage/{userId}/upload-profile")
    public ResponseEntity<?> uploadProfile(@PathVariable final Long userId,
                                           @RequestParam final MultipartFile file) throws IOException{
        Long userUploadProfileId = userService.uploadProfile(userId,file);
        return new ResponseEntity<>(userUploadProfileId,HttpStatus.OK);
    }

}
