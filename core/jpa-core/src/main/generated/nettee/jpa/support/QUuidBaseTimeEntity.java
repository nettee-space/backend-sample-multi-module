package nettee.jpa.support;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUuidBaseTimeEntity is a Querydsl query type for UuidBaseTimeEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QUuidBaseTimeEntity extends EntityPathBase<UuidBaseTimeEntity> {

    private static final long serialVersionUID = -1873323675L;

    public static final QUuidBaseTimeEntity uuidBaseTimeEntity = new QUuidBaseTimeEntity("uuidBaseTimeEntity");

    public final QUuidBaseEntity _super = new QUuidBaseEntity(this);

    public final DateTimePath<java.time.Instant> createdAt = createDateTime("createdAt", java.time.Instant.class);

    //inherited
    public final ComparablePath<java.util.UUID> id = _super.id;

    public final DateTimePath<java.time.Instant> updatedAt = createDateTime("updatedAt", java.time.Instant.class);

    public QUuidBaseTimeEntity(String variable) {
        super(UuidBaseTimeEntity.class, forVariable(variable));
    }

    public QUuidBaseTimeEntity(Path<? extends UuidBaseTimeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUuidBaseTimeEntity(PathMetadata metadata) {
        super(UuidBaseTimeEntity.class, metadata);
    }

}

