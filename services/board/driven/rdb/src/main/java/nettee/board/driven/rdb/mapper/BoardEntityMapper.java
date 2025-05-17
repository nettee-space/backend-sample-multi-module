package nettee.board.driven.rdb.mapper;

import nettee.board.domain.Board;
import nettee.board.driven.rdb.entity.BoardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardEntityMapper {

    Board toDomain(BoardEntity boardEntity);

    BoardEntity toEntity(Board board);

}
