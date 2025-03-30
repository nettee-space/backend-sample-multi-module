package nettee.board.port;

import nettee.board.Board;

public interface BoardCommandRepositoryPort {
    Board save(Board board);
}
