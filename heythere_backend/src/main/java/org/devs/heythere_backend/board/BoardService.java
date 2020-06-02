package org.devs.heythere_backend.board;

import lombok.RequiredArgsConstructor;
import org.devs.heythere_backend.exception.BoardNotFoundException;
import org.devs.heythere_backend.user.User;
import org.devs.heythere_backend.user.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public List<BoardResponseDto> getAllBoardsByBoardId(final Long boardId) {
        return boardRepository.getAllByBoardOwnerId(boardId).stream()
                .map(board -> BoardResponseDto.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .writer(board.getWriter())
                        .view(board.getView())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public BoardResponseDto getBoardById(final Long boardId) {
        Board target = boardRepository.getBoardById(boardId)
                .orElseThrow(() ->
                        new BoardNotFoundException("BOARD NOT FOUND ID : " + boardId))
                .addViewCount();

        return BoardResponseDto.builder()
                .id(target.getId())
                .title(target.getTitle())
                .writer(target.getWriter())
                .content(target.getContent())
                .view(target.getView())
                .comments(target.getComments())
                .build();
    }

    @Transactional
    public Long registerBoard(final Long userId, final Long boardOwnerId,
                              final BoardRegisterRegisterFormDto boardRegisterRegisterFormDto) {
        final User writer = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND ID : " + userId));

        final Board board = Board.builder()
                .boardOwnerId(boardOwnerId)
                .title(boardRegisterRegisterFormDto.getTitle())
                .content(boardRegisterRegisterFormDto.getTitle())
                .user(writer)
                .writer(writer.getName())
                .build();

        return boardRepository.save(board).getId();

    }

    @Transactional
    public BoardModifyResponseDto updateBoard(final Long boardId, final Map<String, String> content) {
        final Board target = boardRepository.getBoardById(boardId)
                .orElseThrow(()
                        -> new BoardNotFoundException("BOARD NOT FOUND ID : " + boardId))
                .updateBoard(content.get("content"));

        return BoardModifyResponseDto.builder()
                .id(target.getId())
                .title(target.getTitle())
                .writer(target.getWriter())
                .content(target.getTitle())
                .build();
    }

    @Transactional
    public boolean deleteBoard(final Long boardId, final Long userId) {
        final Board target = boardRepository.getBoardById(boardId)
                .orElseThrow(()
                        -> new BoardNotFoundException("BOARD NOT FOUND ID : " + boardId));

        if (userId.equals(target.getUser().getId()))
            return false;

        boardRepository.removeById(boardId);
        return true;
    }
}
