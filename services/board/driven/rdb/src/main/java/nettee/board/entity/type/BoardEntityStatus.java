package nettee.board.entity.type;

import me.nettee.type.BoardStatus;
import static me.nettee.BoardQueryErrorCode.DEFAULT;

import me.nettee.type.BoardStatus;

public enum BoardEntityStatus {
    REMOVED(0),
    PENDING(10),
    ACTIVE(20),
    SUSPENDED(30);

    private final int value;

    BoardEntityStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BoardEntityStatus valueOf(BoardStatus boardStatus) {
        return switch (boardStatus){
            case REMOVED -> REMOVED;
            case PENDING -> PENDING;
            case ACTIVE -> ACTIVE;
            case SUSPENDED -> SUSPENDED;
        };
    }

    public static BoardEntityStatus valueOf(int value) {
        return switch (value) {
            case 0 -> REMOVED;
            case 10 -> PENDING;
            case 20 -> ACTIVE;
            case 30 -> SUSPENDED;
            default -> throw DEFAULT.exception();
        };
    }
}