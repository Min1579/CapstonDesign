package org.devs.heythere_backend.board;

import lombok.RequiredArgsConstructor;
import org.devs.heythere_backend.exception.HasNoAuthorityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("register/u/{userId}/b/{boardId}")
    public ResponseEntity<Long> registerComment(@PathVariable("userId") Long userId,
                                                @PathVariable("boardId") Long boardId,
                                                final Map<String, String> reply) {
        final Long commentId = commentService.registerComment(userId, boardId, reply.get("comment"));
        return new ResponseEntity<>(commentId, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{commentId}/u/{userId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId,
                                           @PathVariable("userId") Long userId) {
        if(!commentService.deleteCommentById(commentId, userId))
            return new ResponseEntity<>(new HasNoAuthorityException("No Authorization"), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>("board deleted", HttpStatus.OK);
    }
}
