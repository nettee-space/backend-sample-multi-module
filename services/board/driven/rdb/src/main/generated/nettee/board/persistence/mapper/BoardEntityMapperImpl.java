package nettee.board.persistence.mapper;

import javax.annotation.processing.Generated;
import nettee.board.Board;
import nettee.board.entity.BoardEntity;
import nettee.board.entity.type.BoardEntityStatus;
import nettee.board.type.BoardStatus;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-11T21:31:17+0900",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class BoardEntityMapperImpl implements BoardEntityMapper {

    @Override
    public Board toDomain(BoardEntity boardEntity) {
        if ( boardEntity == null ) {
            return null;
        }

        Board.BoardBuilder board = Board.builder();

        board.id( boardEntity.getId() );
        board.title( boardEntity.getTitle() );
        board.content( boardEntity.getContent() );
        board.status( boardEntityStatusToBoardStatus( boardEntity.getStatus() ) );
        board.createdAt( boardEntity.getCreatedAt() );
        board.updatedAt( boardEntity.getUpdatedAt() );

        return board.build();
    }

    @Override
    public BoardEntity toEntity(Board board) {
        if ( board == null ) {
            return null;
        }

        BoardEntity.BoardEntityBuilder boardEntity = BoardEntity.builder();

        boardEntity.title( board.getTitle() );
        boardEntity.content( board.getContent() );
        boardEntity.status( boardStatusToBoardEntityStatus( board.getStatus() ) );

        return boardEntity.build();
    }

    protected BoardStatus boardEntityStatusToBoardStatus(BoardEntityStatus boardEntityStatus) {
        if ( boardEntityStatus == null ) {
            return null;
        }

        BoardStatus boardStatus;

        switch ( boardEntityStatus ) {
            case REMOVED: boardStatus = BoardStatus.REMOVED;
            break;
            case PENDING: boardStatus = BoardStatus.PENDING;
            break;
            case ACTIVE: boardStatus = BoardStatus.ACTIVE;
            break;
            case SUSPENDED: boardStatus = BoardStatus.SUSPENDED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + boardEntityStatus );
        }

        return boardStatus;
    }

    protected BoardEntityStatus boardStatusToBoardEntityStatus(BoardStatus boardStatus) {
        if ( boardStatus == null ) {
            return null;
        }

        BoardEntityStatus boardEntityStatus;

        switch ( boardStatus ) {
            case PENDING: boardEntityStatus = BoardEntityStatus.PENDING;
            break;
            case ACTIVE: boardEntityStatus = BoardEntityStatus.ACTIVE;
            break;
            case SUSPENDED: boardEntityStatus = BoardEntityStatus.SUSPENDED;
            break;
            case REMOVED: boardEntityStatus = BoardEntityStatus.REMOVED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + boardStatus );
        }

        return boardEntityStatus;
    }
}
