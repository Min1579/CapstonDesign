package org.devs.heythere_backend.video;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class VideoCommentResponseDto {
    private Long userId;
    private Long commentId;
    private String name;
    private String picture;
    private String comment;
    private Integer numOfLargeComment;
    private Integer good;
    private Integer bad;

    @Builder
    public VideoCommentResponseDto(Long userId, Long commentId, String name,
                                   String picture, String comment, Integer numOfLargeComment,
                                   Integer good, Integer bad) {
        this.userId = userId;
        this.commentId = commentId;
        this.name = name;
        this.picture = picture;
        this.comment = comment;
        this.good = good;
        this.bad = bad;
        this.numOfLargeComment = numOfLargeComment;
    }
}
