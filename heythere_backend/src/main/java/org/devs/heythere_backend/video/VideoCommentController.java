package org.devs.heythere_backend.video;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class VideoCommentController {
    private final VideoCommentService videoCommentService;

    @GetMapping("v/{videoId}")
    public ResponseEntity<List<VideoCommentResponseDto>> addCommentToVideoAndReturnComment(@PathVariable("videoId") final Long videoId) {
        List<VideoCommentResponseDto> response = videoCommentService.getAllCommentByVideoId(videoId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("v/{videoId}/u/{userId}")
    public ResponseEntity<List<VideoCommentResponseDto>> addCommentToVideoAndReturnComment(@PathVariable("videoId") final Long videoId,
                                                                                           @PathVariable("userId") final Long userId,
                                                                                           @RequestBody @Valid final Map<String, String> request) {

        List<VideoCommentResponseDto> response = videoCommentService.addCommentToVideoAndReturnComments(userId, videoId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
