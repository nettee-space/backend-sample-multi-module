package nettee.reply.driven.rdb.persistence.mapper;

import java.util.List;
import java.util.Optional;
import nettee.reply.domain.Reply;
import nettee.reply.driven.rdb.entity.ReplyEntity;
import nettee.reply.model.ReplyQueryModels.ReplyDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReplyEntityMapper {

    Reply toDomain(ReplyEntity replyEntity);

    ReplyDetail toReplyDetail(ReplyEntity replyEntity);

    ReplyEntity toEntity(Reply reply);

    List<ReplyEntity> toEntityList(List<Reply> replies);

    List<Reply> toDomainList(List<ReplyEntity> replyEntities);

    default Optional<ReplyDetail> toOptionalReplyDetail(ReplyEntity replyEntity) {
        return Optional.ofNullable(toReplyDetail(replyEntity));
    }

}