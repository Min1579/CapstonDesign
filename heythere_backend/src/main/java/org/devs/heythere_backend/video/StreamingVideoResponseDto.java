package org.devs.heythere_backend.video;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class StreamingVideoResponseDto {
    private Long id;
    private String fileName;
    private String title;
    private String description;
    private int view;
    private String videoUrl;
    private String thumbnailUrl;
    private String username;
    private String picture;

    @Builder
    public StreamingVideoResponseDto(final Long id,
                                     final String fileName,
                                     final String title, final String description, int view, String videoUrl, String thumbnailUrl, String username, String picture) {
        this.id = id;
        this.fileName = fileName;
        this.title = title;
        this.description = description;
        this.view = view;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.username = username;
        this.picture = picture;
    }
}
