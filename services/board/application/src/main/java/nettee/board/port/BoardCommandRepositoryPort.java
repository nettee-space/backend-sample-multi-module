package nettee.board.port;

import nettee.board.domain.Board;

public interface BoardCommandRepositoryPort {
    Board save(Board board);
}
