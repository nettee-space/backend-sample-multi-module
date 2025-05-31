package nettee.draft.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.draft.domain.type.DraftStatus;

import java.time.Instant;
import java.util.Objects;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Draft {
    private Long id;
    private String title;
    private String content;
    private DraftStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private Long blogId;
    private Long articleId;

    public static Draft of(String title, String content) {
        return Draft.builder()
                .title(title)
                .content(content)
                .status(DraftStatus.PENDING) // 기본 상태 설정
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    @Builder(
            builderClassName = "updateDraftBuilder",
            builderMethodName = "prepareDraftUpdate",
            buildMethodName = "update"
    )
    public void update(String title, String content) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");

        this.title = title;
        this.content = content;
        this.updatedAt = Instant.now();
    }

    public void softDelete() { this.status = DraftStatus.DELETED; };
}