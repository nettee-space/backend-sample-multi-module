package nettee.jpa.support;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLongBaseTimeEntity is a Querydsl query type for LongBaseTimeEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QLongBaseTimeEntity extends EntityPathBase<LongBaseTimeEntity> {

    private static final long serialVersionUID = 950453862L;

    public static final QLongBaseTimeEntity longBaseTimeEntity = new QLongBaseTimeEntity("longBaseTimeEntity");

    public final QLongBaseEntity _super = new QLongBaseEntity(this);

    public final DateTimePath<java.time.Instant> createdAt = createDateTime("createdAt", java.time.Instant.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.time.Instant> updatedAt = createDateTime("updatedAt", java.time.Instant.class);

    public QLongBaseTimeEntity(String variable) {
        super(LongBaseTimeEntity.class, forVariable(variable));
    }

    public QLongBaseTimeEntity(Path<? extends LongBaseTimeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLongBaseTimeEntity(PathMetadata metadata) {
        super(LongBaseTimeEntity.class, metadata);
    }

}

