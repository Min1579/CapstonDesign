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
public class Subscriber {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscriber_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Subscriber(User user) {
        this.user = user;
    }

    @Builder
    public Subscriber(Long id, User user) {
        this.id = id;
        this.user = user;
    }
}
