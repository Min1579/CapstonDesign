package org.devs.heythere_backend.board;

import lombok.RequiredArgsConstructor;
import org.devs.heythere_backend.board.comment.CommentEditForm;
import org.devs.heythere_backend.board.comment.CommentEditResponseDto;
import org.devs.heythere_backend.board.comment.CommentService;
import org.devs.heythere_backend.board.comment.CommentWriteForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("board")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    @PostMapping("write")
    public ResponseEntity<Long> writeBoard(@RequestBody BoardWriteForm form){
        Long writeBoardId = boardService.writeBoard(form.toBoardEntity());
        return new ResponseEntity<>(writeBoardId, HttpStatus.CREATED);
    }

    @PutMapping("{boardId}")
    public ResponseEntity<BoardEditResponseDto> editBoard(@PathVariable Long boardId,
                                                          @RequestBody BoardEditForm form){
        BoardEditResponseDto responseDto = boardService.editBoardContent(boardId,form);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    @DeleteMapping("{boardId}")
    public ResponseEntity<Long> deleteBoard(@PathVariable Long boardId){
        return new ResponseEntity<>(boardService.deleteBoard(boardId),HttpStatus.ACCEPTED);
    }
    @PostMapping("{boardId}/comment")
    public ResponseEntity<Long> writeComment(@PathVariable Long boardId,
                                             @RequestBody CommentWriteForm form){
        Long writeCommentId = commentService.writeComment(boardId, form.toCommentEntity());
        return new ResponseEntity<>(writeCommentId,HttpStatus.CREATED);
    }
    @PutMapping("{boardId}/comment/{commentId}")
    public ResponseEntity<CommentEditResponseDto> editComment(@PathVariable Long commentId,
                                                              @RequestBody CommentEditForm form){
        CommentEditResponseDto responseDto = commentService.editComment(commentId,form);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    @DeleteMapping("{boardId}/comment/{commentId}")
    public ResponseEntity<Long> deleteComment(@PathVariable Long commentId){
        return new ResponseEntity<>(commentService.deleteComment(commentId),HttpStatus.ACCEPTED);
    }
}
