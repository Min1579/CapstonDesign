package org.devs.heythere_backend.community;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class CommunityPostResponseDto {
    private String profileImg;
    private String username;
    private String name;
    private String content;
    private String picture;
    private Integer good;
    private Integer bad;
    private Integer numOfComments;

    @Builder
    public CommunityPostResponseDto(String profileImg, String username, String name, String content, String picture, Integer good, Integer bad, Integer numOfComments) {
        this.profileImg = profileImg;
        this.username = username;
        this.name = name;
        this.content = content;
        this.picture = picture;
        this.good = good;
        this.bad = bad;
        this.numOfComments = numOfComments;
    }
}
