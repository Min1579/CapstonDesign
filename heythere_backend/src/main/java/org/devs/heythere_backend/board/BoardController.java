package org.devs.heythere_backend.board;

import lombok.RequiredArgsConstructor;
import org.devs.heythere_backend.exception.HasNoAuthorityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("main/{boardId}")
    public ResponseEntity<List<BoardResponseDto>> getAllBoardByBoardId(@PathVariable("boardId") final Long boardId) {
        final List<BoardResponseDto> allBoardsByBoardId =  boardService.getAllBoardsByBoardId(boardId);
        return new ResponseEntity<>(allBoardsByBoardId, HttpStatus.OK);
    }

    @GetMapping("{boardId}")
    public ResponseEntity<BoardResponseDto> getBoardById(@PathVariable("boardId") final Long boardId) {
        final BoardResponseDto board = boardService.getBoardById(boardId);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PostMapping("{boardOwnerId}/register/{userId}")
    public ResponseEntity<Long> registerBoard(@PathVariable("boardOwnerId") final Long boardOwnerId,
                                              @PathVariable("userId") Long userId,
                                              @RequestBody @Valid BoardRegisterRegisterFormDto boardRegisterRegisterFormDto){
        final Long writeBoardId = boardService.registerBoard(userId, boardOwnerId, boardRegisterRegisterFormDto);
        return new ResponseEntity<>(writeBoardId, HttpStatus.CREATED);
    }

    @PutMapping("update/{boardId}")
    public ResponseEntity<BoardModifyResponseDto> modifyBoard(@PathVariable final Long boardId,
                                                              Map<String, String> content){
        final BoardModifyResponseDto modifyResponseDto = boardService.updateBoard(boardId, content);
        return new ResponseEntity<>(modifyResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("delete/{boardId}/u/{userId}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long boardId,
                                            @PathVariable("userId") Long userId){
        if(!boardService.deleteBoard(boardId, userId))
            return new ResponseEntity<>(new HasNoAuthorityException("No Authorization"), HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>("board deleted", HttpStatus.OK);
    }
}
