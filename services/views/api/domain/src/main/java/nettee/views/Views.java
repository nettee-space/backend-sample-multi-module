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
public class Views {

    private Long postId;

    private Long userId;

    private String ipAddress;

    private String userAgent;
}
