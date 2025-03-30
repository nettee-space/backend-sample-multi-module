package nettee.board.port;

import me.nettee.Board;

public interface BoardCommandRepositoryPort {
    Board save(Board board);
}
