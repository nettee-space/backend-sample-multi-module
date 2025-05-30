package nettee.reply.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReplyEntity is a Querydsl query type for ReplyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReplyEntity extends EntityPathBase<ReplyEntity> {

    private static final long serialVersionUID = 1188766717L;

    public static final QReplyEntity replyEntity = new QReplyEntity("replyEntity");

    public final nettee.jpa.support.QLongBaseTimeEntity _super = new nettee.jpa.support.QLongBaseTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<nettee.reply.entity.type.ReplyEntityStatus> status = createEnum("status", nettee.reply.entity.type.ReplyEntityStatus.class);

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    public QReplyEntity(String variable) {
        super(ReplyEntity.class, forVariable(variable));
    }

    public QReplyEntity(Path<? extends ReplyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReplyEntity(PathMetadata metadata) {
        super(ReplyEntity.class, metadata);
    }

}

