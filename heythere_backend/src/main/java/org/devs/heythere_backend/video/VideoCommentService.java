package org.devs.heythere_backend.video;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devs.heythere_backend.user.User;
import org.devs.heythere_backend.user.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class VideoCommentService {
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    private final VideoCommentRepository videoCommentRepository;
    private final VideoLargeCommentRepository largeCommentRepository;


    @Transactional
    public List<VideoCommentResponseDto> getAllCommentByVideoId(final Long videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(RuntimeException::new)
                .getComments()
                .stream()
                .map(comment -> VideoCommentResponseDto.builder()
                        .userId(comment.getUser().getId())
                        .name(comment.getUser().getName())
                        .picture(comment.getUser().getPicture())
                        .commentId(comment.getId())
                        .comment(comment.getComment())
                        //.numOfLargeComment(comment.getLargeComments().size())
                        .good(comment.getGood())
                        .bad(comment.getBad())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public List<VideoCommentResponseDto> addCommentToVideoAndReturnComments(final Long userId,
                                                                            final Long videoId,
                                                                            final Map<String, String> request) {

        final Video targetVideo = videoRepository.findById(videoId)
                .orElseThrow(RuntimeException::new);

        final VideoComment newComment = videoCommentRepository.save(VideoComment.builder()
                .comment(request.get("comment"))
                .good(0)
                .bad(0)
                .build())
                .updateTargetVideo(targetVideo)
                .updateWriter(userRepository.findById(userId)
                        .orElseThrow(RuntimeException::new));

        log.info("video comment {} created", newComment.getId());

        return videoCommentRepository.findAllByVideo(targetVideo).stream()
                .map(comment -> VideoCommentResponseDto.builder()
                        .userId(comment.getUser().getId())
                        .commentId(comment.getId())
                        .comment(comment.getComment())
                        .good(comment.getGood())
                        .bad(comment.getBad())
                        .name(comment.getUser().getName())
                        .picture(comment.getUser().getPicture())
                        //.numOfLargeComment(newComment.getLargeComments().size())
                        .build()).collect(Collectors.toList());
    }

    @Transactional
    public Long addLargeCommentToComment(final Long userId,
                                         final Long commentId,
                                         final Map<String, String> request) {

        return largeCommentRepository.save(new VideoLargeComment(request.get("comment")))
                .updateTargetComment(videoCommentRepository.findById(commentId)
                        .orElseThrow(() -> new RuntimeException("Comment Not Found")))
                .updateWriter(userRepository.findById(userId)
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found")))
                .getId();
    }

    @Transactional
    public void deleteCommentById(final Long commentId) {
        videoCommentRepository.deleteById(commentId);
    }

    @Transactional
    public void deleteLargeCommentById(final Long largeCommentId) {
        largeCommentRepository.deleteById(largeCommentId);
    }

    @Transactional
    public Long updateCommentById(final Long commentId, final String comment) {
        return videoCommentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment Not Found"))
                .updateComment(comment)
                .getId();
    }

    @Transactional
    public Long updateLargeCommentById(final Long largeCommentId, final String comment) {
        return largeCommentRepository.findById(largeCommentId)
                .orElseThrow(() -> new RuntimeException("Comment Not Found"))
                .updateComment(comment)
                .getId();
    }

}
