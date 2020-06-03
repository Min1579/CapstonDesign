package org.devs.heythere_backend.video;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@NoArgsConstructor
public class VideoCommentsResponseDto {
    private VideoComment comment;
    private List<VideoLargeComment> largeComments = new ArrayList<>();

    @Builder
    public VideoCommentsResponseDto(VideoComment comment, List<VideoLargeComment> largeComments) {
        this.comment = comment;
        this.largeComments = largeComments;
    }
}
