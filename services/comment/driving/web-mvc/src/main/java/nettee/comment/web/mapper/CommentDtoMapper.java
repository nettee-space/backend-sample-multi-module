package nettee.comment.web.mapper;

import nettee.comment.domain.Comment;
import nettee.comment.web.dto.CommentCommandDto.CommentCreateCommand;
import nettee.comment.web.dto.CommentCommandDto.CommentUpdateCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentDtoMapper {
    Comment toDomain(CommentCreateCommand command);
    Comment toDomain(CommentUpdateCommand command);
}
