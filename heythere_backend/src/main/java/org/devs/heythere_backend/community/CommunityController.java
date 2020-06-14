package org.devs.heythere_backend.community;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommunityController {
    private final CommunityService communityService;

    @GetMapping("c/{ownerId}")
    public ResponseEntity<CommunityMainResponseDto> getCommunityOwnerInfo(@PathVariable("ownerId") final Long ownerId) {
        return new ResponseEntity<>(communityService.getCommunityOwnerInfo(ownerId), HttpStatus.OK);
    }

    @PostMapping("c/{ownerId}/post")
    public ResponseEntity<List<CommunityPostResponseDto>>postOnCommunityAndGetAllPosts(@PathVariable("ownerId") final Long ownerId,
                                                                                  @RequestParam("picture") final MultipartFile picture,
                                                                                  @RequestParam("content") final String content) {
        List<CommunityPostResponseDto> response = communityService.postOnCommunityAndGetAllPosts(ownerId, picture, content);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
