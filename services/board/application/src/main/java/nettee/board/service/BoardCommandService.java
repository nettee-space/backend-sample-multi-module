package nettee.board.service;

import lombok.RequiredArgsConstructor;
import me.nettee.Board;
import nettee.board.port.BoardCommandRepositoryPort;
import nettee.board.usecase.BoardCreateUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCommandService implements BoardCreateUseCase {
    
    private final BoardCommandRepositoryPort boardCommandRepositoryPort;
    
    @Override
    public Board createBoard(Board board) {
        return boardCommandRepositoryPort.save(board);
    }
}
