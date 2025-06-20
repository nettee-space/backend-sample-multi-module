package nettee.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardEntity is a Querydsl query type for BoardEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardEntity extends EntityPathBase<BoardEntity> {

    private static final long serialVersionUID = 2065718653L;

    public static final QBoardEntity boardEntity = new QBoardEntity("boardEntity");

    public final nettee.jpa.support.QLongBaseTimeEntity _super = new nettee.jpa.support.QLongBaseTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<nettee.board.entity.type.BoardEntityStatus> status = createEnum("status", nettee.board.entity.type.BoardEntityStatus.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    public QBoardEntity(String variable) {
        super(BoardEntity.class, forVariable(variable));
    }

    public QBoardEntity(Path<? extends BoardEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardEntity(PathMetadata metadata) {
        super(BoardEntity.class, metadata);
    }

}

