package org.devs.heythere_backend.board;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardEditForm {
    private String content;

    @Builder
    public BoardEditForm(String content) {
        this.content = content;
    }
}
