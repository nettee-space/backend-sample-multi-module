package nettee.redis.properties.cache.domain;

public record DomainCacheProperties(
        Long ttl,
        Boolean disableNull,
        String prefix
) {
    public DomainCacheProperties {
        // Default 1 minute
        if (ttl == null) {
            ttl = 60L;
        }else if(ttl < 0) {
            throw new RuntimeException("TTL is set to 'no expiration'. The entry will not expire.");
        }
        // Default NO Cache NULL Value
        if (disableNull == null) {
            disableNull = true;
        }
        
        if (prefix == null) {
            prefix = "";
        }

        prefix = prefix.strip();
    }
}
