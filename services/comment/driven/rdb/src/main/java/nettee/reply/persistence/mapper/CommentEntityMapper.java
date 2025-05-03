package nettee.reply.persistence.mapper;

import nettee.comment.Comment;
import nettee.reply.entity.ReplyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentEntityMapper {

    Comment toDomain(ReplyEntity commentEntity);

    ReplyEntity toEntity(Comment comment);

}