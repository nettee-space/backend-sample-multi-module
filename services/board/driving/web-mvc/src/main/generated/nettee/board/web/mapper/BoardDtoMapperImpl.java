package nettee.board.web.mapper;

import javax.annotation.processing.Generated;
import nettee.board.Board;
import nettee.board.type.BoardStatus;
import nettee.board.web.dto.BoardCommandDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-11T21:31:15+0900",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class BoardDtoMapperImpl implements BoardDtoMapper {

    @Override
    public Board toDomain(BoardCommandDto.BoardCreateCommand command, BoardStatus status) {
        if ( command == null && status == null ) {
            return null;
        }

        Board.BoardBuilder board = Board.builder();

        if ( command != null ) {
            board.title( command.title() );
            board.content( command.content() );
            board.status( command.status() );
        }

        return board.build();
    }
}
