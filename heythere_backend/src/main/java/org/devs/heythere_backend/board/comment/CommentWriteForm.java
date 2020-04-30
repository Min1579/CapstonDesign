package org.devs.heythere_backend.board.comment;

import lombok.Builder;

public class CommentWriteForm {
    private String comment;

    @Builder
    public CommentWriteForm(String comment) {
        this.comment = comment;
    }

    public Comment toCommentEntity(){
        return Comment.builder()
                .comment(comment)
                .build();
    }
}
