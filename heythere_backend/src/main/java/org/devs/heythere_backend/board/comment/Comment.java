package org.devs.heythere_backend.board.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devs.heythere_backend.board.Board;

import javax.persistence.*;

@Setter @Getter
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
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(Long id, String writer, String comment, Board board) {
        this.id = id;
        this.writer = writer;
        this.comment = comment;
        this.board = board;
    }
}

