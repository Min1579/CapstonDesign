package org.devs.heythere_backend.community;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.devs.heythere_backend.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class CommunityPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_post_id")
    private Long id;
    private String content;
    private String picture;
    private Integer good;
    private Integer bad;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public CommunityPost(Long id, String content, String picture, Integer good, Integer bad, User user) {
        this.id = id;
        this.content = content;
        this.picture = picture;
        this.good = good;
        this.bad = bad;
        this.user = user;
    }
}
