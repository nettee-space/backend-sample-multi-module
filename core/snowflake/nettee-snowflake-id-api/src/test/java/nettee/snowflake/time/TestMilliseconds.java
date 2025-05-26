package nettee.snowflake.time;

import nettee.time.MillisecondsSupplier;

public final class TestMilliseconds implements MillisecondsSupplier {
    public long currentMilliseconds;
    public boolean forceBreak = false;
    
    public TestMilliseconds() {
        currentMilliseconds = System.currentTimeMillis();
    }
    
    @Override
    public long getAsLong() {
        if (forceBreak) {
            throw new RuntimeException("Forced break from timeGen");
        }
        
        return currentMilliseconds;
    }
    
    public void nextMillisecond() {
        currentMilliseconds += 1L;
    }
}
