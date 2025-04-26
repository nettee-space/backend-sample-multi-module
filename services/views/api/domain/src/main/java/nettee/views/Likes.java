package nettee.views;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Likes {

    private Long id;

    private Long count;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long userId;

    private Long blogId;

    private Long postId;
}
