package org.devs.heythere_backend.subscribe;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.devs.heythere_backend.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserSubscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_subscriber_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User target;

    @ManyToOne
    @JoinColumn(name="subcriber_id")
    private Subscriber subscriber;

    @Builder
    public UserSubscriber(Long id, User target, Subscriber subscriber) {
        this.id = id;
        this.target = target;
        this.subscriber = subscriber;
    }
}
