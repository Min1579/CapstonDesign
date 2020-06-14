package org.devs.heythere_backend.subscribe;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devs.heythere_backend.user.User;

import javax.persistence.*;

@Setter @Getter
@NoArgsConstructor
@Entity
public class Subscribe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User target;

    private Long subscriber;

    public Subscribe(User target, Long subscriber) {
        this.target = target;
        this.subscriber = subscriber;
    }

    @Builder
    public Subscribe(Long id, User target, Long subscriber) {
        this.id = id;
        this.target = target;
        this.subscriber = subscriber;
    }
}
