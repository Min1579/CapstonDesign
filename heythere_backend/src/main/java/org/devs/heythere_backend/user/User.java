package org.devs.heythere_backend.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devs.heythere_backend.board.Board;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter @Getter
@NoArgsConstructor
@Entity
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username", "email"})
        }
)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String name;
    private String email;
    private String password;
    private String picture;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board> boards = new ArrayList<>();

    @Builder
    public User(Long id, String username, String name, String email, String password, String picture, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.roles = roles;
    }


}
