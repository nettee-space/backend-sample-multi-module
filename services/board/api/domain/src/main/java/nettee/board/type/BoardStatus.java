package nettee.board.type;

import nettee.common.EnumUtil;

import java.util.EnumSet;
import java.util.Set;

public enum BoardStatus {

    PENDING(0),
    ACTIVE(10),
    SUSPENDED(20),
    REMOVED(30);

    private final int code;

    BoardStatus(int code) {
        this.code = code;
    }

    static {
        assert EnumUtil.isUniqueAllOf(BoardStatus.class, BoardStatus::getCode) : "Duplicate code";
    }

    private static final Set<BoardStatus> GENERAL_QUERY_STATUS = EnumSet.of(ACTIVE, SUSPENDED);

    public static Set<BoardStatus> getGeneralQueryStatus() {
       return GENERAL_QUERY_STATUS;
    }

    public int getCode() { return this.code; }
}
