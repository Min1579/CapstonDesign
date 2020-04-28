package org.devs.heythere_backend.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devs.heythere_backend.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Setter @Getter
@NoArgsConstructor
@Entity
public class Board  {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String writer;

    @Column(columnDefinition = "CLOB")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Board(Long id, String title, String writer,  String content, User user) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.user = user;
    }
}
