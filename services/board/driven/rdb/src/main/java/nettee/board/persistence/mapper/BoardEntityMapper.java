package nettee.board.persistence.mapper;

import me.nettee.Board;
import me.nettee.BoardQueryModels.BoardDetail;
import me.nettee.BoardQueryModels.BoardSummary;
import nettee.board.entity.BoardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardEntityMapper {

    Board toDomain(BoardEntity boardEntity);

    BoardEntity toEntity(Board board);

    BoardDetail toDetail(BoardEntity boardEntity);

    BoardSummary toSummary(BoardEntity boardEntity);

}
