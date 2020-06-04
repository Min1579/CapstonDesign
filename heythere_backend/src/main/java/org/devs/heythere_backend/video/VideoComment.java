package org.devs.heythere_backend.video;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devs.heythere_backend.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class VideoComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @NotBlank
    private String comment;

    private int good;
    private int bad;

    @OneToMany(mappedBy = "videoComment")
    private List<VideoLargeComment> largeComments = new ArrayList<>();


    public VideoComment(@NotBlank String comment) {
        this.comment = comment;
    }

    @Builder
    public VideoComment(Long id, User user, Video video, String comment, List<VideoLargeComment> largeComments) {
        this.id = id;
        this.user = user;
        this.video = video;
        this.comment = comment;
        this.largeComments = largeComments;
    }

    public VideoComment updateWriter(final User user) {
        this.setUser(user);
        return this;
    }

    public VideoComment updateTargetVideo(final Video video) {
        this.setVideo(video);
        video.getComments().add(this);
        return this;
    }

    public VideoComment updateComment(final String comment) {
        this.setComment(comment);
        return this;
    }

    public VideoComment updateGood() {
        this.setGood(good + 1);
        return this;
    }

    public VideoComment updateCancelGood() {
        this.setGood(good - 1);
        return this;
    }

    public VideoComment updateBad() {
        this.setBad(bad + 1);
        return this;
    }

    public VideoComment updateCancelBad() {
        this.setBad(bad - 1);
        return this;
    }

}
