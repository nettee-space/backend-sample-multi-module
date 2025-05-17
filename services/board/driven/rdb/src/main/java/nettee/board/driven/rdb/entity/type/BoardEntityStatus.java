package nettee.board.driven.rdb.entity.type;

import nettee.board.domain.type.BoardStatus;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static nettee.board.exception.BoardErrorCode.DEFAULT;

public enum BoardEntityStatus {
    REMOVED(
            SemanticCodeParameters.builder()
                    .canRead(false)
                    .classifyingBits(0b0000_0000_0000_0000)
    ),
    PENDING(
            SemanticCodeParameters.builder()
                    .canRead(false)
                    .classifyingBits(0b0000_0000_0000_0001)
    ),
    ACTIVE(
            SemanticCodeParameters.builder()
                    .canRead(true)
                    .classifyingBits(0b0000_0000_0000_0010)
    ),
    SUSPENDED(
            SemanticCodeParameters.builder()
                    .canRead(true)
                    .classifyingBits(0b0000_0000_0000_0100)
    );

    /*
    R000 0000 0000 0000 0PPP PPPP PPPP PPPP
      R: generally readable status (1: readable, 0: unreadable)
      0: classifying bits (16 bits)
      P: detailed or padded bits (15 bits)
     */
    private static final int TLB_PADDING_SIZE = 31;
    private static final int CLASSIFYING_PADDING_SIZE = 15;

    private final int code;

    static {
        // NOTE util 함수가 추가되면 리팩토링
        assert Arrays.stream(values())
                .map(BoardEntityStatus::getCode)
                .collect(Collectors.toSet())
                .size()
                == values().length
                : "BoardEntityStatus의 모든 code 필드가 고유해야 합니다.";
    }

    BoardEntityStatus(SemanticCodeParameters<Present, Present> semanticCodeParameters) {
        this(
                semanticCodeParameters.canRead,
                semanticCodeParameters.classifyingBits,
                semanticCodeParameters.detailBits
        );
    }

    BoardEntityStatus(boolean canRead, int classifyingBits, int detailBits) {
        this.code = (canRead ? 1 << TLB_PADDING_SIZE : 0)
                | (classifyingBits << CLASSIFYING_PADDING_SIZE)
                | detailBits;
    }

    public int getCode() {
        return code;
    }

    public static BoardEntityStatus valueOf(BoardStatus boardStatus) {
        assert Set.of(BoardStatus.REMOVED, BoardStatus.PENDING, BoardStatus.ACTIVE, BoardStatus.SUSPENDED)
                .containsAll(Arrays.stream(BoardStatus.values()).collect(Collectors.toSet()))
                : "BoardStatus 중 일부가 BoardEntityStatus::valueOf 함수에서 매핑되지 않습니다.";

        return switch (boardStatus){
            case REMOVED -> REMOVED;
            case PENDING -> PENDING;
            case ACTIVE -> ACTIVE;
            case SUSPENDED -> SUSPENDED;
            default -> throw new Error("BoardStatus 중 일부가 BoardEntityStatus::valueOf 함수에서 매핑되지 않습니다.");
        };
    }

    public static BoardEntityStatus valueOf(int value) {
        return switch (value) {
            case 0b0__0000_0000_0000_0000__000_0000_0000_0000 -> REMOVED;
            case 0b0__0000_0000_0000_0001__000_0000_0000_0000 -> PENDING;
            case 0b1__0000_0000_0000_0010__000_0000_0000_0000 -> ACTIVE;
            case 0b1__0000_0000_0000_0100__000_0000_0000_0000 -> SUSPENDED;
            default -> throw DEFAULT.exception();
        };
    }

    static class SemanticCodeParameters<HAS_CAN_READ, HAS_CLASSIFYING_BITS> {
        boolean canRead;
        Integer classifyingBits;
        int detailBits;

        private SemanticCodeParameters() {}

        public static SemanticCodeParameters<Missing, Missing> builder() {
            return new SemanticCodeParameters<>();
        }

        SemanticCodeParameters<HAS_CAN_READ, Present> classifyingBits(Integer classifyingBits) {
            this.classifyingBits = classifyingBits;
            return (SemanticCodeParameters<HAS_CAN_READ, Present>) this;
        }

        SemanticCodeParameters<Present, HAS_CLASSIFYING_BITS> canRead(boolean canRead) {
            this.canRead = canRead;
            return (SemanticCodeParameters<Present, HAS_CLASSIFYING_BITS>) this;
        }

        SemanticCodeParameters<HAS_CAN_READ, HAS_CLASSIFYING_BITS> detailBits(int detailBits) {
            this.detailBits = detailBits;
            return this;
        }

    }

    // NOTE move to other module after its place is determined
    // Marker interfaces
    interface Missing {}
    interface Present {}
}