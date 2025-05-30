package nettee.jpa.support;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUuidBaseEntity is a Querydsl query type for UuidBaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QUuidBaseEntity extends EntityPathBase<UuidBaseEntity> {

    private static final long serialVersionUID = 519412408L;

    public static final QUuidBaseEntity uuidBaseEntity = new QUuidBaseEntity("uuidBaseEntity");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public QUuidBaseEntity(String variable) {
        super(UuidBaseEntity.class, forVariable(variable));
    }

    public QUuidBaseEntity(Path<? extends UuidBaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUuidBaseEntity(PathMetadata metadata) {
        super(UuidBaseEntity.class, metadata);
    }

}

