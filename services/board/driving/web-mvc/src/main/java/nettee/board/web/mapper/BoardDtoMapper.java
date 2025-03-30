package nettee.board.web.mapper;

import nettee.board.Board;
import nettee.board.type.BoardStatus;
import nettee.board.web.dto.BoardCommandDto.BoardCreateCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardDtoMapper {

    Board toDomain(BoardCreateCommand command, BoardStatus status);

}
