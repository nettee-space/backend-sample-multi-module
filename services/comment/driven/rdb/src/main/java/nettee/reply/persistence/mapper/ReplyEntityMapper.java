package nettee.reply.persistence.mapper;

import java.util.Optional;
import nettee.reply.Reply;
import nettee.reply.entity.ReplyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReplyEntityMapper {

    Reply toDomain(ReplyEntity replyEntity);

    ReplyEntity toEntity(Reply reply);

    default Optional<Reply> toOptionalDomain(ReplyEntity replyEntity) {
        return Optional.ofNullable(toDomain(replyEntity));
    }

}