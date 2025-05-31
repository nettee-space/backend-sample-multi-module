package nettee.time;

public final class SystemMilliseconds implements MillisecondsSupplier {
    @Override
    public long getAsLong() {
        return System.currentTimeMillis();
    }
}
