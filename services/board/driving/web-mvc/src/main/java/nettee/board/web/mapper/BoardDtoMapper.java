package nettee.board.web.mapper;

import me.nettee.Board;
import me.nettee.type.BoardStatus;
import nettee.board.web.dto.BoardCommandDto.BoardCreateCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardDtoMapper {

    Board toDomain(BoardCreateCommand command, BoardStatus status);

}
