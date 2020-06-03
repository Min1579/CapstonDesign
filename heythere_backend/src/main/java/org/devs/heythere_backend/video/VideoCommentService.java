package org.devs.heythere_backend.video;

import lombok.RequiredArgsConstructor;
import org.devs.heythere_backend.user.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VideoCommentService {
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    private final VideoCommentRepository commentRepository;
    private final VideoLargeCommentRepository largeCommentRepository;


    @Transactional
    public List<VideoCommentsResponseDto> getAllCommentByVideoId(final Long videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video Not Found"))
                .getComments()
                .stream()
                .map(comment -> VideoCommentsResponseDto.builder()
                        .comment(comment)
                        .largeComments(comment.getLargeComments())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public Long addCommentToVideo(final Long userId,
                                  final Long videoId,
                                  final Map<String, String> request) {

        return commentRepository.save(new VideoComment(request.get("comment")))
                .updateTargetVideo(videoRepository.findById(videoId)
                        .orElseThrow(() -> new RuntimeException("VIDEO NOT FOUND")))
                .updateWriter(userRepository.findById(userId)
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found")))
                .getId();
    }

    @Transactional
    public Long addLargeCommentToComment(final Long userId,
                                         final Long commentId,
                                         final Map<String, String> request) {

        return largeCommentRepository.save(new VideoLargeComment(request.get("comment")))
                .updateTargetComment(commentRepository.findById(commentId)
                        .orElseThrow(() -> new RuntimeException("Comment Not Found")))
                .updateWriter(userRepository.findById(userId)
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found")))
                .getId();
    }

    @Transactional
    public void deleteCommentById(final Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Transactional
    public void deleteLargeCommentById(final Long largeCommentId) {
        largeCommentRepository.deleteById(largeCommentId);
    }

    @Transactional
    public Long updateCommentById(final Long commentId, final String comment) {
        return commentRepository.findById(commentId)
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
