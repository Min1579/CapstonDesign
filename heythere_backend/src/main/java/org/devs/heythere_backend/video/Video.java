package org.devs.heythere_backend.video;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.devs.heythere_backend.model.BaseTimeEntity;
import org.devs.heythere_backend.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Video extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "video_id")
    private Long id;
    private String fileName;
    private String title;
    private String description;

    @Column(columnDefinition = "integer default 0")
    private Integer view;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String videoUrl;
    private String thumbnailUrl;

    @Column(columnDefinition = "integer default 0")
    private Integer good = 0;

    @Column(columnDefinition = "integer default 0")
    private Integer bad = 0;
    @OneToMany(mappedBy = "video")
    private List<VideoComment> comments = new ArrayList<>();

    @Builder
    public Video(String fileName, String title, String description, Integer view, User user, String videoUrl, String thumbnailUrl, List<VideoComment> comments, Integer good, Integer bad) {
         this.fileName = fileName;
        this.title = title;
        this.description = description;
        this.view = view;
        this.user = user;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.comments = comments;
        this.good = good;
        this.bad = bad;
    }

    public Video updateView() {
        view++;
        return this;
    }

    public Video updateGood() {
        good++;
        return this;
    }

    public Video updateBad() {
        bad++;
        return this;
    }

    public void setStreamingUrl(final String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
