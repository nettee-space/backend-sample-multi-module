package nettee.comment.persistence.mapper;

import java.util.Optional;
import nettee.comment.Comment;
import nettee.comment.entity.CommentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentEntityMapper {

    Comment toDomain(CommentEntity commentEntity);

    CommentEntity toEntity(Comment comment);

    default Optional<Comment> toOptionalDomain(CommentEntity commentEntity) {
        return Optional.ofNullable(toDomain(commentEntity));
    }
}