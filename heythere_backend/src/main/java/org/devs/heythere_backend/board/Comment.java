package org.devs.heythere_backend.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devs.heythere_backend.user.User;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String writer;

    @Column(columnDefinition = "CLOB")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public void setTargetBoard(final Board board) {
        this.board = board;
        board.getComments().add(this);
    }

    @Builder
    public Comment(Long id, String writer, String comment, Board board, User user) {
        this.id = id;
        this.writer = writer;
        this.comment = comment;
        this.board = board;
        this.user = user;
    }
}

