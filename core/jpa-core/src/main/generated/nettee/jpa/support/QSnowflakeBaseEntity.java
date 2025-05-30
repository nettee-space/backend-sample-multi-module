package nettee.jpa.support;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSnowflakeBaseEntity is a Querydsl query type for SnowflakeBaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QSnowflakeBaseEntity extends EntityPathBase<SnowflakeBaseEntity> {

    private static final long serialVersionUID = -1627228643L;

    public static final QSnowflakeBaseEntity snowflakeBaseEntity = new QSnowflakeBaseEntity("snowflakeBaseEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QSnowflakeBaseEntity(String variable) {
        super(SnowflakeBaseEntity.class, forVariable(variable));
    }

    public QSnowflakeBaseEntity(Path<? extends SnowflakeBaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSnowflakeBaseEntity(PathMetadata metadata) {
        super(SnowflakeBaseEntity.class, metadata);
    }

}

