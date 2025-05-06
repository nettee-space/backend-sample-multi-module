package nettee.snowflake.exception;

import static nettee.snowflake.constants.SnowflakeConstants.SnowflakeDefault.MAX_WORKER_ID;

public class InvalidWorkerIdException extends RuntimeException{
    
    private final long workerId;
    
    public InvalidWorkerIdException(final long workerId) {
        super("Worker ID can't be greater than %d or less than 0. Input: %d"
                .formatted(MAX_WORKER_ID, workerId));
        this.workerId = workerId;
    }
    
    public long getWorkerId() {
        return workerId;
    }
}
