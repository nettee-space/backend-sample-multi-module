package nettee.draft.entity.type;

import nettee.draft.entity.type.builder.TypeSafeMarkers.Present;
import nettee.draft.type.DraftStatus;


import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static nettee.draft.DraftCommandErrorCode.DEFAULT;

public enum DraftEntityStatus {
    DELETED(
        DraftStatusParameters.builder()
                .canRead(false)
                .classifyingBits(0b0000_0000_0000_0000)
    ),
    PENDING(
        DraftStatusParameters.builder()
                .canRead(false)
                .classifyingBits(0b0000_0000_0000_0001)
    ),
    DRAFT(
        DraftStatusParameters.builder()
                .canRead(true)
                .classifyingBits(0b0000_0000_0000_0010)
    ),
    DONE(
        DraftStatusParameters.builder()
                .canRead(true)
                .classifyingBits(0b0000_0000_0000_0100)
    );

    private static final int TLB_PADDING_SIZE = 31;
    private static final int CLASSIFYING_PADDING_SIZE = 15;
    private final int code;

    static {
        assert Arrays.stream(values())
                .map(DraftEntityStatus::getCode)
                .collect(Collectors.toSet())
                .size()
                == values().length
                : "DraftEntityStatus의 모든 code 필드가 고유해야 합니다.";
    }

    DraftEntityStatus(DraftStatusParameters<Present, Present> draftStatusParameters) {
        this(
                draftStatusParameters.canRead,
                draftStatusParameters.classifyingBits,
                draftStatusParameters.detailBits
        );
    }

    DraftEntityStatus(boolean canRead, int classifyingBits, int detalBits) {
        this.code = (canRead ? 1 << TLB_PADDING_SIZE : 0)
                | (classifyingBits << CLASSIFYING_PADDING_SIZE)
                | detalBits;
    }

    public int getCode() { return code; }

    public static DraftEntityStatus valueOf(DraftStatus draftStatus) {
        assert Set.of(DraftStatus.DELETED, DraftStatus.PENDING, DraftStatus.DRAFT, DraftStatus.DONE)
                .containsAll(Arrays.stream(DraftStatus.values()).collect(Collectors.toSet()))
                : "DraftStatus 중 일부가 DraftEntityStatus::valueOf 함수에서 매핑되지 않습니다.";

        return switch (draftStatus) {
            case DELETED -> DELETED;
            case PENDING -> PENDING;
            case DRAFT -> DRAFT;
            case DONE -> DONE;
            default -> throw new Error("DraftStatus 중 일부가 DraftEntityStatus::valueOf 함수에서 매핑되지 않습니다.");
        };
    }

    public static DraftEntityStatus valueOf(int value) {
        return switch (value) {
            case 0b0__0000_0000_0000_0000__000_0000_0000_0000 -> DELETED;
            case 0b0__0000_0000_0000_0001__000_0000_0000_0000 -> PENDING;
            case 0b1__0000_0000_0000_0010__000_0000_0000_0000 -> DRAFT;
            case 0b1__0000_0000_0000_0100__000_0000_0000_0000 -> DONE;
            default -> throw DEFAULT.exception();
        };
    }
}
