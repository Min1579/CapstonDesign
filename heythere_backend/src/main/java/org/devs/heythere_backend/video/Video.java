package org.devs.heythere_backend.video;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.devs.heythere_backend.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Video {
    @Id @GeneratedValue
    @Column(name = "video_id")
    private Long id;
    private String title;
    private String description;
    private int view;
    @ManyToMany
    private User user;
    private String videoUrl;

    @Builder
    public Video(String title, String description, int view, User user, String videoUrl) {
        this.title = title;
        this.description = description;
        this.view = view;
        this.user = user;
        this.videoUrl = videoUrl;
    }
}
