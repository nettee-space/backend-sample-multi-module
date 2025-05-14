package nettee.board.persistence.mapper;

import nettee.board.domain.Board;
import nettee.board.entity.BoardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardEntityMapper {

    Board toDomain(BoardEntity boardEntity);

    BoardEntity toEntity(Board board);

}
