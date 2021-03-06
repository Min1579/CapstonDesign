package org.devs.heythere_backend.board;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentEditForm {
    private String comment;

    @Builder
    public CommentEditForm(String comment) {
        this.comment = comment;
    }
}
