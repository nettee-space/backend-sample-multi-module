package nettee.board;

import lombok.RequiredArgsConstructor;
import nettee.board.persistence.mapper.BoardEntityMapper;
import nettee.board.port.BoardCommandRepositoryPort;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import static nettee.board.BoardCommandErrorCode.DEFAULT;

@Repository
@RequiredArgsConstructor
public class BoardCommandAdapter implements BoardCommandRepositoryPort {

    private final BoardJpaRepository boardJpaRepository;
    private final BoardEntityMapper boardEntityMapper;

    @Override
    public Board save(Board board) {
        var boardEntity = boardEntityMapper.toEntity(board);
        try {
            var newBoard = boardJpaRepository.save(boardEntity);
            boardJpaRepository.flush();
            return boardEntityMapper.toDomain(newBoard);
        } catch (DataAccessException e) {
            throw DEFAULT.exception(e);
        }
    }

}