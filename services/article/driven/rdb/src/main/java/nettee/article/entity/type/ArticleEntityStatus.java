package nettee.article.entity.type;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import nettee.article.entity.type.builder.TypeSafeMarkers.Present;
import nettee.article.domain.type.ArticleStatus;

import static nettee.article.ArticleCommandErrorCode.DEFAULT;

public enum ArticleEntityStatus {
    DELETED(
        ArticleStatusParameters.builder()
                .canRead(false)
                .classifyingBits(0b0000_0000_0000_0000)
    ),
    PENDING(
        ArticleStatusParameters.builder()
                .canRead(false)
                .classifyingBits(0b0000_0000_0000_0001)
    ),
    ACTIVE(
        ArticleStatusParameters.builder()
                .canRead(true)
                .classifyingBits(0b0000_0000_0000_0010)
    ),
    SUSPENDED(
        ArticleStatusParameters.builder()
                .canRead(true)
                .classifyingBits(0b0000_0000_0000_0100)
    );

    private static final int TLB_PADDING_SIZE = 31;
    private static final int CLASSIFYING_PADDING_SIZE = 15;
    private final int code;

    static {
        assert Arrays.stream(values())
                .map(ArticleEntityStatus::getCode)
                .collect(Collectors.toSet())
                .size()
                == values().length
                : "ArticleEntityStatus의 모든 code 필드가 고유해야 합니다.";
    }

    ArticleEntityStatus(ArticleStatusParameters<Present, Present> articleStatusParameters) {
        this(
                articleStatusParameters.canRead,
                articleStatusParameters.classifyingBits,
                articleStatusParameters.detailBits
        );
    }

    ArticleEntityStatus(boolean canRead, int classifyingBits, int detalBits) {
        this.code = (canRead ? 1 << TLB_PADDING_SIZE : 0)
                | (classifyingBits << CLASSIFYING_PADDING_SIZE)
                | detalBits;
    }

    public int getCode() { return code; }

    public static ArticleEntityStatus valueOf(ArticleStatus articleStatus) {
        assert Set.of(ArticleStatus.DELETED, ArticleStatus.PENDING, ArticleStatus.ACTIVE, ArticleStatus.SUSPENDED)
                .containsAll(Arrays.stream(ArticleStatus.values()).collect(Collectors.toSet()))
                : "ArticleStatus 중 일부가 ArticleEntityStatus::valueOf 함수에서 매핑되지 않습니다.";

        return switch (articleStatus) {
            case DELETED -> DELETED;
            case PENDING -> PENDING;
            case ACTIVE -> ACTIVE;
            case SUSPENDED -> SUSPENDED;
            default -> throw new Error("ArticleStatus 중 일부가 ArticleEntityStatus::valueOf 함수에서 매핑되지 않습니다.");
        };
    }

    public static ArticleEntityStatus valueOf(int value) {
        return switch (value) {
            case 0b0__0000_0000_0000_0000__000_0000_0000_0000 -> DELETED;
            case 0b0__0000_0000_0000_0001__000_0000_0000_0000 -> PENDING;
            case 0b1__0000_0000_0000_0010__000_0000_0000_0000 -> ACTIVE;
            case 0b1__0000_0000_0000_0100__000_0000_0000_0000 -> SUSPENDED;
            default -> throw DEFAULT.exception();
        };
    }
}
