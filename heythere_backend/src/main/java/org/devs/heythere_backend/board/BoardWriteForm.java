package org.devs.heythere_backend.board;

import lombok.Builder;

public class BoardWriteForm {
    private String title;
    private String content;

    @Builder
    public BoardWriteForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Board toBoardEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}
