package org.devs.heythere_backend.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devs.heythere_backend.user.User;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private User boardOwner;
    private String title;
    private String writer;
    private String content;
    private int view;
    private User user;
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public BoardResponseDto(Long id, User boardOwner, String title,
                            String writer, String content, int view,
                            User user, List<Comment> comments) {
        this.id = id;
        this.boardOwner = boardOwner;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.view = view;
        this.user = user;
        this.comments = comments;
    }
}
