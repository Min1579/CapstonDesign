package org.devs.heythere_backend.board;

import lombok.RequiredArgsConstructor;
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

    @PutMapping("{boardId}/edit")
    public ResponseEntity<BoardEditResponseDto> editBoard(@PathVariable Long boardId,
                                                          @RequestBody BoardEditForm form){
        BoardEditResponseDto responseDto = boardService.editBoardContent(boardId,form);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    @DeleteMapping("{boardId}/delete")
    public ResponseEntity<Long> deleteBoard(@PathVariable Long boardId){
        return new ResponseEntity<>(boardService.deleteBoard(boardId),HttpStatus.ACCEPTED);
    }
    @PostMapping("{boardId}/write-comment")
    public ResponseEntity<Long> writeComment(@PathVariable Long boardId,
                                             @RequestBody CommentWriteForm form){
        Long writeCommentId = commentService.writeComment(boardId, form.toCommentEntity());
        return new ResponseEntity<>(writeCommentId,HttpStatus.CREATED);
    }
}
