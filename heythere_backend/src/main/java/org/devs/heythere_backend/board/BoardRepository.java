package org.devs.heythere_backend.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> getBoardById(final Long id);
    void removeById(final Long boardId);

    List<Board> getAllByBoardOwnerId(Long boardOwnerId);
}
