package org.devs.heythere_backend.community;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class CommunityMainResponseDto {
    private String name;
    private String picture;
    //구독자수
    @Builder
    public CommunityMainResponseDto(String name, String picture) {
        this.name = name;
        this.picture = picture;
    }
}
