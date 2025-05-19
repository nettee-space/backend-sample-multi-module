package nettee.reply.persistence.mapper;

import java.util.Optional;
import nettee.reply.Reply;
import nettee.reply.entity.ReplyEntity;
import nettee.reply.model.ReplyQueryModels.ReplyDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReplyEntityMapper {

    Reply toDomain(ReplyEntity replyEntity);

    ReplyDetail toReplyDetail(ReplyEntity replyEntity);

    ReplyEntity toEntity(Reply reply);

    default Optional<ReplyDetail> toOptionalReplyDetail(ReplyEntity replyEntity) {
        return Optional.ofNullable(toReplyDetail(replyEntity));
    }

}