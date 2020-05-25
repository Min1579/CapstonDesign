package org.devs.heythere_backend.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    //@PreAuthorize("hasAnyRole('USER')")
    @GetMapping("mypage/{userId}")
    public ResponseEntity<UserMypageResponseDto> findUserByIdAndSendToMypage(@PathVariable("userId") final Long userId) {
        final UserMypageResponseDto response = userService.findUserByIdAndSendToMypage(userId);
        log.info("mypage");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("mypage/{serId}/edit-profile")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> editPicture(@PathVariable final Long serId,
                                         @RequestParam MultipartFile file
                                        ) throws NameNotFoundException, IOException {
        // file save in resources/profile
        String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/profile/";
        Path fileNameAndPath = Paths.get(uploadDirectory + file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());

        final Long userEditId = userService.editProfile(serId,"http://localhost:8080/profile/" + file.getOriginalFilename());
        return new ResponseEntity<>(userEditId, HttpStatus.OK);
    }

}
