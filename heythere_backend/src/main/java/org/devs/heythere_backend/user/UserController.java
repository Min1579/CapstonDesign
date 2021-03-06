package org.devs.heythere_backend.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;


    /* for register */
    @PostMapping("user/check/email")
    public ResponseEntity<UserDuplicatedCheckResponseDto> checkDuplicatedEmail(@RequestBody final Map<String, String> request) {
        return new ResponseEntity<>(userService.checkDuplicatedEmail(request), HttpStatus.OK);
    }

    @PostMapping("user/check/username")
    public ResponseEntity<UserDuplicatedCheckResponseDto> checkDuplicatedUsername(@RequestBody final Map<String, String> request) {
        return new ResponseEntity<>(userService.checkDuplicatedUsername(request), HttpStatus.OK);
    }

    @PostMapping("user/check/name")
    public ResponseEntity<UserDuplicatedCheckResponseDto> checkDuplicatedName(@RequestBody final Map<String, String> request) {
        return new ResponseEntity<>(userService.checkDuplicatedName(request), HttpStatus.OK);
    }

    /* user register */
    @PostMapping("user/register/0")
    public ResponseEntity<Long> register(@RequestBody @Valid final UserRegisterFormRequestDto request) {
        log.info("request come");
        return new ResponseEntity<>(userService.register(request), HttpStatus.OK);
    }

    @PostMapping(value = "user/register/1",
            headers = "Content-Type=multipart/form-data",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Long> registerWithImg(@RequestParam("img") final MultipartFile img,
                                                @RequestParam("email") final String email,
                                                @RequestParam("username") final String username,
                                                @RequestParam("name") final String name,
                                                @RequestParam("pwd") final String pwd,
                                                @RequestParam("introduction") final String introduction) {
        Long userId = userService.registerWithImg(img, email, username, name, pwd, introduction);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @GetMapping("user/search/{usernameOrNameOrEmail}")
    public ResponseEntity<?> searchByUsernameOrNameOrEmail(@PathVariable final String usernameOrNameOrEmail) {
        List<UserResearchFoundResponseDto> users = userService.searchByUsernameOrNameOrEmail(usernameOrNameOrEmail);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("user/streaming")
    public ResponseEntity<List<UserOnStreamingResponseDto>> findAllUserOnStreaming() {
        return new ResponseEntity<>(userService.findAllUserOnStreaming(), HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole('USER')")
    @GetMapping("user/mypage/{userId}")
    public ResponseEntity<UserMypageResponseDto> findUserByIdAndSendToMypage(@PathVariable("userId") final Long userId) {
        final UserMypageResponseDto response = userService.findUserByIdAndSendToMypage(userId);
        log.info("mypage");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("user/mypage/img/{userId}")
    public ResponseEntity<Map<String, String>> getUserProfilePicture(@PathVariable("userId") final Long userId) {
        Map<String, String> response = new HashMap<>();
        response.put("picture", userService.getUserPictureById(userId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping(value = "user/mypage/upload/img/{userId}",
            headers = "Content-Type=multipart/form-data",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, "application/x-www-form-urlencoded"})
    public ResponseEntity<Map<String, String>> updateUserProfileImg(@PathVariable("userId") final Long userId,
                                                                    @RequestParam("picture") final MultipartFile picture) {
        Map<String, String> response = new HashMap<>();
        response.put("picture", userService.updatePicture(userId, picture));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("user/mypage/profile/valid/{input}/t/{type}")
    public ResponseEntity<Map<String, Boolean>> validationChecker(
            @PathVariable("input") final String input,
            @PathVariable("type") final int type) {

        Map<String, Boolean> response = new HashMap<>();
        if (type == 0)
            response.put("valid", userService.existByUsername(input));

        if (type == 1)
            response.put("valid", userService.existByName(input));
        if (type == 2)
            response.put("valid", userService.existByEmail(input));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("user/mypage/{userId}/profile/t/{type}")
    public ResponseEntity<Map<String, String>> updateUserProfile(
            @PathVariable("userId") final Long userId,
            @PathVariable("type") final int type,
            @RequestBody @Valid final Map<String, String> request) {

        Map<String, String> response = new HashMap<>();

        if (type == 0)
            response.put("username", userService.updateUsername(userId, request.get("username")));

        if (type == 1)
            response.put("name", userService.updateName(userId, request.get("name")));

        if (type == 2)
            response.put("email", userService.updateEmail(userId, request.get("email")));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
