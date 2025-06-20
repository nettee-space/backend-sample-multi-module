package nettee.comment.driven.rdb.persistence.mapper;

import java.util.Optional;
import nettee.comment.domain.Comment;
import nettee.comment.driven.rdb.entity.CommentEntity;
import nettee.comment.model.CommentQueryModels.CommentDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentEntityMapper {

    Comment toDomain(CommentEntity commentEntity);
    CommentDetail toCommentDetail(CommentEntity commentEntity);
    CommentEntity toEntity(Comment comment);

    default Optional<CommentDetail> toOptionalCommentDetail(CommentEntity commentEntity) {
        return Optional.ofNullable(toCommentDetail(commentEntity));
    }
}