package nettee.reply.web.mapper;

import nettee.reply.domain.Reply;
import nettee.reply.web.dto.ReplyCommandDto.ReplyCreateCommand;
import nettee.reply.web.dto.ReplyCommandDto.ReplyUpdateCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReplyDtoMapper {
    Reply toDomain(ReplyCreateCommand command);
    Reply toDomain(ReplyUpdateCommand command);
}
