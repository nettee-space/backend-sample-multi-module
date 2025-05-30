package nettee.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommentEntity is a Querydsl query type for CommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentEntity extends EntityPathBase<CommentEntity> {

    private static final long serialVersionUID = 2101173661L;

    public static final QCommentEntity commentEntity = new QCommentEntity("commentEntity");

    public final nettee.jpa.support.QLongBaseTimeEntity _super = new nettee.jpa.support.QLongBaseTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<nettee.comment.entity.type.CommentEntityStatus> status = createEnum("status", nettee.comment.entity.type.CommentEntityStatus.class);

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    public QCommentEntity(String variable) {
        super(CommentEntity.class, forVariable(variable));
    }

    public QCommentEntity(Path<? extends CommentEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommentEntity(PathMetadata metadata) {
        super(CommentEntity.class, metadata);
    }

}

