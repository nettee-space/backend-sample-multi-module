package nettee.board.application.service;

import lombok.RequiredArgsConstructor;
import nettee.board.domain.Board;
import nettee.board.application.port.BoardCommandRepositoryPort;
import nettee.board.application.usecase.BoardCreateUseCase;
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
