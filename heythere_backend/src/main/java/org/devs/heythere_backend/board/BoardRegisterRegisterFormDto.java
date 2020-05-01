package org.devs.heythere_backend.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class BoardRegisterRegisterFormDto {
    private String title;
    private String content;

    @Builder
    public BoardRegisterRegisterFormDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Board toBoardEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}
