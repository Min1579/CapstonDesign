package org.devs.heythere_backend.subscribe;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("subscribe")
@RestController
public class SubscribeController {

    private final SubscribeService subscribeService;

    @GetMapping("{targetId}/u/{userId}")
    public ResponseEntity<Map<String, Boolean>> subscribeUser(
            @PathVariable("targetId") final Long targetId,
            @PathVariable("userId") final Long userId) {

            Map<String, Boolean> map = new HashMap<>();
            Boolean res = subscribeService.doSubscribe(targetId, userId);
            map.put("subscribe", res);
            return !res ?
                new ResponseEntity<>(map, HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(map, HttpStatus.OK);
    }
}
