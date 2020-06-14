package org.devs.heythere_backend.system;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("main")
public class HomeController {
    private final SystemService systemService;

    @GetMapping("user/on")
    public ResponseEntity<List<UserStatusOnUserListResponseDto>> getAllUserByStatusIsOn() {
        final List<UserStatusOnUserListResponseDto> response
                = systemService.getAllUserByStatusIsOn();
        log.info("On : {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
