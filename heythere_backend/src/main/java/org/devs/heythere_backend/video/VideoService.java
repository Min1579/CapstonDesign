package org.devs.heythere_backend.video;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devs.heythere_backend.config.SaveFilePath;
import org.devs.heythere_backend.user.User;
import org.devs.heythere_backend.user.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@RequestMapping("video")
public class VideoService {
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    private synchronized boolean saveVideoToLocalServer(final MultipartFile video) {
        final String path = String.format("%s%s", SaveFilePath.FILE_VIDEO_SAVE_DIR, video.getOriginalFilename());

        try {
            byte[] file = video.getBytes();
            final FileOutputStream fos = new FileOutputStream(path);
            fos.write(file);
            fos.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private synchronized boolean saveThumbnailToLocalServer(final MultipartFile thumbnail) {
        final String path = String.format("%s%s", SaveFilePath.FILE_THUMBNAIL_SAVE_DIR, thumbnail.getOriginalFilename());

        try {
            byte[] file = thumbnail.getBytes();
            final FileOutputStream fos = new FileOutputStream(path);
            fos.write(file);
            fos.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Transactional
    public Long save(final Long userId, final String title, final String description,
                     final MultipartFile video, final MultipartFile thumbnail) {
        final User videoOwner = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND!"));
        if (!saveVideoToLocalServer(video)) {
            throw new RuntimeException("Video Save Failed!");
        }

        if (!saveThumbnailToLocalServer(thumbnail)) {
            throw new RuntimeException("Thumbnail Save Failed!");
        }

        final Video uploadVideo = videoRepository.save(Video.builder()
                .fileName(video.getOriginalFilename())
                .title(title)
                .description(description)
                .view(0)
                .good(0)
                .bad(0)
                .thumbnailUrl(String.format("%s%s", "http://localhost:8080/thumbnail/",
                        thumbnail.getOriginalFilename()))
                .user(videoOwner)
                .build()
        );

        uploadVideo.setStreamingUrl("http://localhost:8080/video/stream/" + uploadVideo.getId());
        return uploadVideo.getId();
    }

    @Transactional
    public String findVideoNameById(final Long videoId) {
        final Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video Not found"));
        return video.getFileName();
    }

    @Transactional
    public VideoResponseDto findById(final Long videoId) {
        final Video video =  videoRepository.findById(videoId)
                .orElseThrow(RuntimeException::new)
                .updateView();

        return VideoResponseDto.builder()
                .id(video.getId())
                .title(video.getTitle())
                .description(video.getDescription())
                .view(video.getView())
                .thumbnailUrl(video.getThumbnailUrl())
                .videoUrl(video.getVideoUrl())
                .username(video.getUser().getName())
                .picture(video.getUser().getPicture())
                .good(video.getGood())
                .bad(video.getBad())
                .build();
    }

    @Transactional
    public List<VideoResponseDto> findAll() {
        return videoRepository.findAll().stream()
                .map(video -> VideoResponseDto.builder()
                        .id(video.getId())
                        .title(video.getTitle())
                        .description(video.getDescription())
                        .view(video.getView())
                        .thumbnailUrl(video.getThumbnailUrl())
                        .videoUrl(video.getVideoUrl())
                        .username(video.getUser().getName())
                        .picture(video.getUser().getPicture())
                        .good(video.getGood())
                        .bad(video.getBad())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public List<VideoResponseDto> findAllById(final Long videoId) {
        final User user = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("video not found"))
                .getUser();

        return videoRepository.findAllByUser(user).stream()
                .map(v -> VideoResponseDto.builder()
                        .id(v.getId())
                        .username(v.getUser().getUsername())
                        .thumbnailUrl(v.getThumbnailUrl())
                        .view(v.getView())
                        .description(v.getDescription())
                        .title(v.getTitle())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public Integer updateGood(final Long videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(RuntimeException::new)
                .updateGood()
                .getGood();
    }

    @Transactional
    public Integer updateBad(final Long videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(RuntimeException::new)
                .updateBad()
                .getBad();
    }
}

