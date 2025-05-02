package nettee.post.entity.type;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import nettee.post.entity.type.builder.TypeSafeMarkers.Present;
import nettee.post.type.PostStatus;
import static nettee.post.PostCommandErrorCode.DEFAULT;

public enum PostEntityStatus {
    DELETED(
        PostStatusParameters.builder()
                .canRead(false)
                .classifyingBits(0b0000_0000_0000_0000)
    ),
    PENDING(
        PostStatusParameters.builder()
                .canRead(false)
                .classifyingBits(0b0000_0000_0000_0001)
    ),
    ACTIVE(
        PostStatusParameters.builder()
                .canRead(true)
                .classifyingBits(0b0000_0000_0000_0010)
    ),
    SUSPENDED(
        PostStatusParameters.builder()
                .canRead(true)
                .classifyingBits(0b0000_0000_0000_0100)
    );

    private static final int TLB_PADDING_SIZE = 31;
    private static final int CLASSIFYING_PADDING_SIZE = 15;
    private final int code;

    static {
        assert Arrays.stream(values())
                .map(PostEntityStatus::getCode)
                .collect(Collectors.toSet())
                .size()
                == values().length
                : "PostEntityStatus의 모든 code 필드가 고유해야 합니다.";
    }

    PostEntityStatus(PostStatusParameters<Present, Present> postStatusParameters) {
        this(
                postStatusParameters.canRead,
                postStatusParameters.classifyingBits,
                postStatusParameters.detailBits
        );
    }

    PostEntityStatus(boolean canRead, int classifyingBits, int detalBits) {
        this.code = (canRead ? 1 << TLB_PADDING_SIZE : 0)
                | (classifyingBits << CLASSIFYING_PADDING_SIZE)
                | detalBits;
    }

    public int getCode() { return code; }

    public static PostEntityStatus valueOf(PostStatus postStatus) {
        assert Set.of(PostStatus.DELETED, PostStatus.PENDING, PostStatus.ACTIVE, PostStatus.SUSPENDED)
                .containsAll(Arrays.stream(PostStatus.values()).collect(Collectors.toSet()))
                : "PostStatus 중 일부가 PostEntityStatus::valueOf 함수에서 매핑되지 않습니다.";

        return switch (postStatus) {
            case DELETED -> DELETED;
            case PENDING -> PENDING;
            case ACTIVE -> ACTIVE;
            case SUSPENDED -> SUSPENDED;
            default -> throw new Error("PostStatus 중 일부가 PostEntityStatus::valueOf 함수에서 매핑되지 않습니다.");
        };
    }

    public static PostEntityStatus valueOf(int value) {
        return switch (value) {
            case 0b0__0000_0000_0000_0000__000_0000_0000_0000 -> DELETED;
            case 0b0__0000_0000_0000_0001__000_0000_0000_0000 -> PENDING;
            case 0b1__0000_0000_0000_0010__000_0000_0000_0000 -> ACTIVE;
            case 0b1__0000_0000_0000_0100__000_0000_0000_0000 -> SUSPENDED;
            default -> throw DEFAULT.exception();
        };
    }
}
