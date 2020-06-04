package org.devs.heythere_backend.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment , Long> {
    Optional<Comment> getCommentById(Long commentId);
    Long removeById(Long commentId);
}
