package nettee.board.application.port;

import nettee.board.domain.Board;

public interface BoardCommandRepositoryPort {
    Board save(Board board);
}
