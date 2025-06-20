package nettee.snowflake.time;

import nettee.time.MillisecondsSupplier;

public final class TestMilliseconds implements MillisecondsSupplier {
    public long currentMilliseconds;
    
    public TestMilliseconds() {
        currentMilliseconds = System.currentTimeMillis();
    }
    
    @Override
    public long getAsLong() {
        return currentMilliseconds;
    }
    
    public void nextMillisecond() {
        currentMilliseconds += 1L;
    }
}
