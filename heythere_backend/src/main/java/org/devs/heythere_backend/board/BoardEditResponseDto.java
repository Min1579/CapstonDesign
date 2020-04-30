package org.devs.heythere_backend.board;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardEditResponseDto {
    private final Long id;

    @Builder
    public BoardEditResponseDto(final Board board) {
        this.id = board.getId();
    }
}
