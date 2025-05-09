package nettee.draft.entity.type;

import nettee.draft.entity.type.builder.TypeSafeMarkers;
import nettee.draft.entity.type.builder.TypeSafeMarkers.Missing;
import nettee.draft.entity.type.builder.TypeSafeMarkers.Present;

public class DraftStatusParameters<
        HAS_CAN_READ extends TypeSafeMarkers,
        HAS_CLASSIFYING_BITS extends TypeSafeMarkers
        > {
    boolean canRead;
    Integer classifyingBits;
    int detailBits;
    private DraftStatusParameters() {}

    public static DraftStatusParameters<Missing, Missing> builder() { return new DraftStatusParameters<>(); }

    public static DraftStatusParameters<Missing, Missing> generate() {
        return new DraftStatusParameters<>();
    }

    @SuppressWarnings("unchecked")
    public DraftStatusParameters<HAS_CAN_READ, Present> classifyingBits(Integer classifyingBits) {
        this.classifyingBits = classifyingBits;
        return (DraftStatusParameters<HAS_CAN_READ, Present>) this;
    }

    @SuppressWarnings("unchecked")
    public DraftStatusParameters<Present, HAS_CLASSIFYING_BITS> canRead(boolean canRead) {
        this.canRead = canRead;
        return (DraftStatusParameters<Present, HAS_CLASSIFYING_BITS>) this;
    }

    public DraftStatusParameters<HAS_CAN_READ, HAS_CLASSIFYING_BITS> detailBits(int detailBits) {
        this.detailBits = detailBits;
        return this;
    }

}
