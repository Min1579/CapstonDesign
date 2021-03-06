package org.devs.heythere_backend.video;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devs.heythere_backend.config.SaveFilePath;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("video")
public class VideoController {
    private final VideoService videoService;

    @GetMapping
    public ResponseEntity<List<VideoResponseDto>> getStreamingVideos() {
        List<VideoResponseDto> videos = videoService.findAll();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @GetMapping("{videoId}")
    public ResponseEntity<VideoResponseDto> getStreamingVideo(@PathVariable("videoId") final Long videoId) {
        log.info("response get");
        return new ResponseEntity<>(videoService.findById(videoId),HttpStatus.OK );
    }



    @PostMapping(value = "upload/{userId}",
            headers = "Content-Type= multipart/form-data",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, "application/x-www-form-urlencoded"})
    public ResponseEntity<Long> uploadThumbnailAndVideo(@PathVariable("userId") final Long userId,
                                              @RequestParam("title") final String title,
                                              @RequestParam("description") final String description,
                                              @RequestParam("video") final MultipartFile video,
                                              @RequestParam("thumbnail") final MultipartFile thumbnail) {
        log.info("received img : {}, video : {} ", thumbnail.getOriginalFilename(),
                                                   video.getOriginalFilename());
        Long videoId = videoService.save(userId, title, description, video, thumbnail);
        return new ResponseEntity<>(videoId, HttpStatus.OK);
    }

    /* streaming controller */
    @GetMapping("stream/{videoId}")
    public StreamingResponseBody stream(@PathVariable("videoId") final Long id) throws FileNotFoundException {
        final File video = new File(SaveFilePath.FILE_VIDEO_SAVE_DIR +videoService.findVideoNameById(id));
        final InputStream is = new FileInputStream(video);
        return os -> {
            readAndWrite(is, os);
        };
    }

    @GetMapping("retrieve/{videoId}")
    public ResponseEntity<List<VideoResponseDto>> retrieveVideos(@PathVariable("videoId") final Long videoId) {
        List<VideoResponseDto> response = videoService.findAllById(videoId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void readAndWrite(final InputStream is, OutputStream os) throws IOException {
        byte[] data = new byte[2048*4];
        int read = 0;
        while ((read = is.read(data)) > 0) {
            os.write(data, 0, read);
        }
        os.flush();
    }

    @GetMapping("good/{videoId}")
    public ResponseEntity<Map<String,Integer>> updateGood(@PathVariable("videoId") final Long videoId) {
        Map<String, Integer>map = new HashMap<>();
        map.put("good", videoService.updateGood(videoId));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("bad/{videoId}")
    public ResponseEntity<Map<String,Integer>> updateBad(@PathVariable("videoId") final Long videoId) {
        Map<String, Integer>map = new HashMap<>();
        map.put("bad", videoService.updateBad(videoId));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
