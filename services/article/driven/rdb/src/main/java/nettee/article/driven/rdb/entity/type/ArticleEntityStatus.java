package nettee.article.driven.rdb.entity.type;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import nettee.article.domain.type.ArticleStatus;
import nettee.common.marker.TypeSafeMarker.Present;
import nettee.common.status.StatusCodeUtil;
import nettee.common.status.StatusParameters;
import nettee.common.status.StatusParameters.GeneralPurposeFeatures;

import static nettee.article.exception.ArticleCommandErrorCode.DEFAULT;

public enum ArticleEntityStatus {
    DELETED(
            StatusParameters.generate()
                    .generalPurposeFeatures(
                            GeneralPurposeFeatures.READ,
                            GeneralPurposeFeatures.SUBITEM_READ
                    )
                    .categoryBits(0b0000_0000_0000_0000)
                    .instanceBits(0)
    ),
    PENDING(
            StatusParameters.generate()
                    .categoryBits(0b0000_0000_0000_0001)
                    .instanceBits(0)
    ),
    ACTIVE(
            StatusParameters.generate()
                    .generalPurposeFeatures(GeneralPurposeFeatures.ALL)
                    .categoryBits(0b0000_0000_0000_0010)
                    .instanceBits(0)
    ),
    SUSPENDED(
            StatusParameters.generate()
                    .generalPurposeFeatures(
                            GeneralPurposeFeatures.READ,
                            GeneralPurposeFeatures.SUBITEM_READ
                    )
                    .categoryBits(0b0000_0000_0000_0100)
                    .instanceBits(0)
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

    ArticleEntityStatus(StatusParameters<Present, Present> articleStatusParameters) {
        this(
                StatusCodeUtil.getAsInt(articleStatusParameters)
        );
    }

    ArticleEntityStatus(int code) { this.code = code; }

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
