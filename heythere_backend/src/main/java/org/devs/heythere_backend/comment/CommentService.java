package org.devs.heythere_backend.comment;

import lombok.RequiredArgsConstructor;
import org.devs.heythere_backend.board.Board;
import org.devs.heythere_backend.board.BoardRepository;
import org.devs.heythere_backend.exception.BoardNotFoundException;
import org.devs.heythere_backend.user.User;
import org.devs.heythere_backend.user.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long registerComment(final Long userId, final Long boardId, final String reply){
        final User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("USER NOT FOUND ID : " + userId));

        final Board board = boardRepository.getBoardById(boardId)
                .orElseThrow(() ->
                        new BoardNotFoundException("BOARD NOT FOUND ID : " + boardId));

        final Comment comment = Comment.builder()
                .writer(user.getName())
                .user(user)
                .comment(reply)
                .build();

        comment.setTargetBoard(board); // <->

        return commentRepository.save(comment).getId();
    }

    @Transactional
    public boolean deleteCommentById(final Long commentId, final Long userId) {
        final Comment target = commentRepository.getCommentById(commentId)
                .orElseThrow(()
                        -> new BoardNotFoundException("Comment NOT FOUND ID : " + commentId));

        if (userId.equals(target.getUser().getId()))
            return false;

        commentRepository.removeById(commentId);
        return true;
    }
}
