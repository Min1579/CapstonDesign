package org.devs.heythere_backend.community;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.devs.heythere_backend.model.BaseTimeEntity;
import org.devs.heythere_backend.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class CommunityPostComment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_post_comment_id")
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "community_post_id")
    private CommunityPost target;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Integer good;
    private Integer bad;

    @Builder
    public CommunityPostComment(Long id, String content, CommunityPost target, User user, Integer good, Integer bad) {
        this.id = id;
        this.content = content;
        this.target = target;
        this.user = user;
        this.good = good;
        this.bad = bad;
    }
}
