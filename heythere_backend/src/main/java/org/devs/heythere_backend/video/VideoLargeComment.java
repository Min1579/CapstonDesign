package org.devs.heythere_backend.video;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devs.heythere_backend.user.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Setter @Getter
@NoArgsConstructor
@Entity
public class VideoLargeComment {
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "video_comment_id")
    private VideoComment videoComment;

    @NotBlank
    private String comment;

    public VideoLargeComment(@NotBlank String comment) {
        this.comment = comment;
    }

    @Builder
    public VideoLargeComment(Long id, User user, VideoComment videoComment, @NotBlank String comment) {
        this.id = id;
        this.user = user;
        this.videoComment = videoComment;
        this.comment = comment;
    }

    public VideoLargeComment updateWriter(final User user) {
        this.setUser(user);
        return this;
    }

    public VideoLargeComment updateComment(final String comment){
        this.setComment(comment);
        return this;
    }

    public VideoLargeComment updateTargetComment(final VideoComment targetComment) {
        this.setVideoComment(targetComment);
        targetComment.getLargeComments().add(this);
        return this;
    }
}
