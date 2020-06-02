package org.devs.heythere_backend.video;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.devs.heythere_backend.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Video {
    @Id @GeneratedValue
    @Column(name = "video_id")
    private Long id;
    private String fileName;
    private String title;
    private String description;
    private int view;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String videoUrl;
    private String thumbnailUrl;

    @OneToMany(mappedBy = "video")
    private List<VideoComment> comments = new ArrayList<>();

    @Builder
    public Video(String fileName, String title, String description, int view, User user, String videoUrl, String thumbnailUrl, List<VideoComment> comments) {
        this.fileName = fileName;
        this.title = title;
        this.description = description;
        this.view = view;
        this.user = user;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.comments = comments;
    }

    public void setStreamingUrl(final String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
