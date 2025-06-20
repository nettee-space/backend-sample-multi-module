package nettee.jpa.support;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSnowflakeBaseTimeEntity is a Querydsl query type for SnowflakeBaseTimeEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QSnowflakeBaseTimeEntity extends EntityPathBase<SnowflakeBaseTimeEntity> {

    private static final long serialVersionUID = 1041103434L;

    public static final QSnowflakeBaseTimeEntity snowflakeBaseTimeEntity = new QSnowflakeBaseTimeEntity("snowflakeBaseTimeEntity");

    public final QSnowflakeBaseEntity _super = new QSnowflakeBaseEntity(this);

    public final DateTimePath<java.time.Instant> createdAt = createDateTime("createdAt", java.time.Instant.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.time.Instant> updatedAt = createDateTime("updatedAt", java.time.Instant.class);

    public QSnowflakeBaseTimeEntity(String variable) {
        super(SnowflakeBaseTimeEntity.class, forVariable(variable));
    }

    public QSnowflakeBaseTimeEntity(Path<? extends SnowflakeBaseTimeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSnowflakeBaseTimeEntity(PathMetadata metadata) {
        super(SnowflakeBaseTimeEntity.class, metadata);
    }

}

