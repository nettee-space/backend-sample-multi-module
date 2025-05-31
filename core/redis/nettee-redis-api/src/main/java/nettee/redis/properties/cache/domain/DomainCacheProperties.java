package nettee.redis.properties.cache.domain;

public record DomainCacheProperties(
        long ttl,
        Boolean disableNull,
        String prefix
) {
    public DomainCacheProperties {
        // Default 1 minute
        if (ttl == 0L) {
            ttl = 60L;
        }
        // Default NO Cache NULL Value
        if (disableNull == null) {
            disableNull = true;
        }
        
        if (prefix != null) {
            prefix = prefix.strip();
        }
    }
}
