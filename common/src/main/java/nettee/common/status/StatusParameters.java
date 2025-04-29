package nettee.common.status;

import nettee.common.marker.TypeSafeMarker;
import nettee.common.marker.TypeSafeMarker.Missing;
import nettee.common.marker.TypeSafeMarker.Present;

public class StatusParameters<
        C extends TypeSafeMarker,
        I extends TypeSafeMarker
> {
    private int generalPurposeBits;
    private int systemInfoBits;

    private int categoryBits;
    private int instanceBits;

    private StatusParameters() {}

    public static StatusParameters<Missing, Missing> generate() {
        return new StatusParameters<>();
    }

    public StatusParameters<C, I> generalPurposeFeatures(GeneralPurposeFeatures... features) {
        generalPurposeBits = 0;
        for (var feature : features) {
            generalPurposeBits |= feature.bit;
        }
        return this;
    }

    public StatusParameters<C, I> systemInfoBits(int systemInfoBits) {
        this.systemInfoBits = systemInfoBits;
        return this;
    }

    public StatusParameters<Present, I> categoryBits(int categoryBits) {
        this.categoryBits = categoryBits;
        @SuppressWarnings("unchecked")
        var instance = (StatusParameters<Present, I>) this;
        return instance;
    }

    public StatusParameters<C, Present> instanceBits(int instanceBits) {
        this.instanceBits = instanceBits;
        @SuppressWarnings("unchecked")
        var instance = (StatusParameters<C, Present>) this;
        return instance;
    }

    public int generalPurposeBits() {
        return generalPurposeBits;
    }

    public int systemInfoBits() {
        return systemInfoBits;
    }

    public int categoryBits() {
        return categoryBits;
    }

    public int instanceBits() {
        return instanceBits;
    }

    public enum GeneralPurposeFeatures {
        ALL(0b110_1100),
        READ(0b100_0000),
        UPDATE(0b010_0000),
        SUBITEM_READ(0b000_1000),
        SUBITEM_UPDATE(0b000_0100);

        private final int bit;

        GeneralPurposeFeatures(int bit) {
            this.bit = bit;
        }
    }
}