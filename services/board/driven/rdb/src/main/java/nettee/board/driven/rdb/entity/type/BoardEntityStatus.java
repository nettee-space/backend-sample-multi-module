package nettee.board.driven.rdb.entity.type;

import nettee.board.domain.type.BoardStatus;
import nettee.common.marker.TypeSafeMarker.Present;
import nettee.common.status.StatusCodeUtil;
import nettee.common.status.StatusParameters;
import nettee.common.status.StatusParameters.GeneralPurposeFeatures;
import nettee.common.util.EnumUtil;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static nettee.board.exception.BoardErrorCode.UNMAPPED_BOARD_STATUS;

public enum BoardEntityStatus {
    REMOVED(
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

    private final int code;

    static {
        assert EnumUtil.isUniqueAllOf(BoardEntityStatus.class, BoardEntityStatus::getCode)
                : "BoardEntityStatus의 모든 code 필드가 고유해야 합니다.";
    }

    BoardEntityStatus(StatusParameters<Present, Present> semanticCodeParameters) {
        this(StatusCodeUtil.getAsInt(semanticCodeParameters));
    }

    BoardEntityStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static BoardEntityStatus valueOf(BoardStatus boardStatus) {
        assert Set.of(BoardStatus.REMOVED, BoardStatus.PENDING, BoardStatus.ACTIVE, BoardStatus.SUSPENDED)
                .containsAll(Arrays.stream(BoardStatus.values()).collect(Collectors.toSet()))
                : "BoardStatus 중 일부가 BoardEntityStatus 인스턴스에 매핑되지 않습니다.";

        return switch (boardStatus){
            case REMOVED -> REMOVED;
            case PENDING -> PENDING;
            case ACTIVE -> ACTIVE;
            case SUSPENDED -> SUSPENDED;
            default -> throw new Error("BoardStatus 중 일부가 BoardEntityStatus 인스턴스에 매핑되지 않습니다.");
        };
    }

    public static BoardEntityStatus valueOf(int value) {
        return switch (value) {
            /*
             * ø: 부호 비트 (사용 안 함)
             * X: 기능 비트 (일반 목적 비트)
             * S: System info bits
             * C: Category bits
             * I: Instance detail bits

                   ø_XXX_XXXX__SSSS_SSSS__CCCC_CCCC__IIII_IIII */
            case 0b0_100_1000__0000_0000__0000_0000__0000_0000 -> REMOVED;
            case 0b0_000_0000__0000_0000__0000_0001__0000_0000 -> PENDING;
            case 0b0_110_1100__0000_0000__0000_0010__0000_0000 -> ACTIVE;
            case 0b0_100_1000__0000_0000__0000_0100__0000_0000 -> SUSPENDED;
            default -> throw UNMAPPED_BOARD_STATUS.exception();
        };
    }
}