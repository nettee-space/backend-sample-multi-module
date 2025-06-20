package nettee.jpa.support;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLongBaseEntity is a Querydsl query type for LongBaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QLongBaseEntity extends EntityPathBase<LongBaseEntity> {

    private static final long serialVersionUID = 2084417593L;

    public static final QLongBaseEntity longBaseEntity = new QLongBaseEntity("longBaseEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QLongBaseEntity(String variable) {
        super(LongBaseEntity.class, forVariable(variable));
    }

    public QLongBaseEntity(Path<? extends LongBaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLongBaseEntity(PathMetadata metadata) {
        super(LongBaseEntity.class, metadata);
    }

}

