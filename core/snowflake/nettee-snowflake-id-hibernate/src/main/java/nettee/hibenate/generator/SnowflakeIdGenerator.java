package nettee.hibenate.generator;

import nettee.snowflake.persistence.id.Snowflake;
import nettee.snowflake.properties.SnowflakeProperties;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class SnowflakeIdGenerator implements IdentifierGenerator {
    
    private final Snowflake snowflake;
    
    public SnowflakeIdGenerator(SnowflakeProperties snowflakeProperties) {
        this.snowflake = new Snowflake(snowflakeProperties);
    }
    
    @Override
    public Long generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return snowflake.nextId();
    }
}
