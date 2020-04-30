package org.devs.heythere_backend.board.comment;

import lombok.RequiredArgsConstructor;
import org.devs.heythere_backend.board.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    final BoardRepository boardRepository;
    final CommentRepository repository;

    @Transactional
    public Long writeComment(Long boardId, Comment toCommentEntity){
        toCommentEntity.setBoard(boardRepository.getOne(boardId));
        return repository.save(toCommentEntity).getId();
    }
}
