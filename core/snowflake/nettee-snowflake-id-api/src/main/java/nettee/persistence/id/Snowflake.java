package nettee.persistence.id;

import nettee.properties.SnowflakeProperties;
import nettee.validator.SnowflakeConstructingValidator;

import static nettee.constants.SnowflakeConstants.NETTEE_EPOCH;
import static nettee.constants.SnowflakeConstants.SnowflakeDefault.*;

public class Snowflake {
    private final long datacenterId;
    private final long workerId;
    private final long epoch;
    
    private long sequence = 0L;
    private long lastTimestamp = -1L;
    
    public Snowflake(SnowflakeProperties properties) {
        this(properties.datacenterId(), properties.workerId(), properties.epoch());
    }
    
    public Snowflake(long datacenterId, long workerId, long epoch) {
        SnowflakeConstructingValidator.validateDatacenterId(datacenterId);
        SnowflakeConstructingValidator.validateWorkerId(workerId);
        
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.epoch = epoch >= 0 ? epoch : NETTEE_EPOCH;
    }
    
    public synchronized long nextId() {
        long timestamp = timeGen();
        
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp
            ));
        }
        
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        
        lastTimestamp = timestamp;
        return ((timestamp - epoch) << TIMESTAMP_LEFT_SHIFT) |
                (datacenterId << DATACENTER_ID_SHIFT) |
                (workerId << WORKER_ID_SHIFT) |
                sequence;
    }
    
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
    
    private long timeGen() {
        return System.currentTimeMillis();
    }
}
