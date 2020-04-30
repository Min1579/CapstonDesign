package org.devs.heythere_backend.board.comment;

import lombok.RequiredArgsConstructor;
import org.devs.heythere_backend.board.BoardEditForm;
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
    @Transactional
    public CommentEditResponseDto editComment(Long commentId, CommentEditForm form){
        Comment comment = repository.getOne(commentId);
        comment.setComment(form.getComment());
        return new CommentEditResponseDto(comment);
    }
    @Transactional
    public Long deleteComment(Long commentId){
        return repository.removeById(commentId);
    }
}
