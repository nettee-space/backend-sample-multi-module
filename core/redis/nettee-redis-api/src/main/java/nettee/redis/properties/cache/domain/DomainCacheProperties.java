package nettee.redis.properties.cache.domain;

public record DomainCacheProperties(
        Long ttl,
        Boolean disableNull,
        String prefix
) {
    public DomainCacheProperties {
        if(ttl != null && ttl < 0L) {
            throw new IllegalArgumentException("TTL must be zero or a positive number. Negative TTL is not allowed.");
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
