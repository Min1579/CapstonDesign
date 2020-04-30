package org.devs.heythere_backend.board.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment , Long> {
}
