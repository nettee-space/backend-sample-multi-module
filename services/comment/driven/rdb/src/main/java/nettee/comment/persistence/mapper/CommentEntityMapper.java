package nettee.comment.persistence.mapper;

import nettee.comment.Comment;
import nettee.comment.entity.CommentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentEntityMapper {

    Comment toDomain(CommentEntity commentEntity);

    CommentEntity toEntity(Comment comment);

}