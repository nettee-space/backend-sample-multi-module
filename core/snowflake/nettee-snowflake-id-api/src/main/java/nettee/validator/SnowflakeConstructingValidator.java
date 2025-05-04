package nettee.validator;

import nettee.exception.InvalidDatacenterIdException;
import nettee.exception.InvalidWorkerIdException;

import static nettee.constants.SnowflakeConstants.SnowflakeDefault.MAX_DATACENTER_ID;
import static nettee.constants.SnowflakeConstants.SnowflakeDefault.MAX_WORKER_ID;

public class SnowflakeConstructingValidator {
    private SnowflakeConstructingValidator() {}
    
    public static void validateDatacenterId(long datacenterId) {
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new InvalidDatacenterIdException(datacenterId);
        }
    }
    
    public static void validateWorkerId(long workerId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new InvalidWorkerIdException(workerId);
        }
    }
}
