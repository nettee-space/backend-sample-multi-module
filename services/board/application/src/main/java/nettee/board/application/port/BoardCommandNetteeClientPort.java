package nettee.board.application.port;

import nettee.board.domain.Board;

public interface BoardCommandNetteeClientPort {
    Board save(Board board);
}
