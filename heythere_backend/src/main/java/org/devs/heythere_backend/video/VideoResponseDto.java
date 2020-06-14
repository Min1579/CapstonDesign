package org.devs.heythere_backend.video;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class VideoResponseDto {
    private Long id;
    private String fileName;
    private String title;
    private String description;
    private Integer view;
    private String videoUrl;
    private String thumbnailUrl;
    private String username;
    private String picture;
    private Integer good;
    private Integer bad;

    @Builder
    public VideoResponseDto(final Long id,
                            final String fileName,
                            final String title,
                            final String description,
                            final Integer view, final String videoUrl,
                            final String thumbnailUrl,
                            final String username,
                            final String picture,
                            final Integer good,
                            final Integer bad) {
        this.id = id;
        this.fileName = fileName;
        this.title = title;
        this.description = description;
        this.view = view;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.username = username;
        this.picture = picture;
        this.good = good;
        this.bad = bad;
    }
}
