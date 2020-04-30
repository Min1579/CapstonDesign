package org.devs.heythere_backend.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository repository;

    @Transactional
    public Long writeBoard(Board toBoardEntity){
        return repository.save(toBoardEntity).getId();
    }

    @Transactional
    public BoardEditResponseDto editBoardContent(Long boardId, BoardEditForm form){
        Board board = repository.getOne(boardId);
        board.setContent(form.getContent());
        return new BoardEditResponseDto(board);
    }
    @Transactional
    public Long deleteBoard(Long boardId){
        return repository.removeById(boardId);
    }
}
