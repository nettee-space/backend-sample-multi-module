package nettee.post.entity.type;

import nettee.post.entity.type.builder.TypeSafeMarkers;
import nettee.post.entity.type.builder.TypeSafeMarkers.Present;
import nettee.post.entity.type.builder.TypeSafeMarkers.Missing;

public class PostStatusParameters<
        HAS_CAN_READ extends TypeSafeMarkers,
        HAS_CLASSIFYING_BITS extends TypeSafeMarkers
        > {
    boolean canRead;
    Integer classifyingBits;
    int detailBits;
    private PostStatusParameters() {}

    public static PostStatusParameters<Missing, Missing> builder() { return new PostStatusParameters<>(); }

    public static PostStatusParameters<Missing, Missing> generate() {
        return new PostStatusParameters<>();
    }

    @SuppressWarnings("unchecked")
    public PostStatusParameters<HAS_CAN_READ, Present> classifyingBits(Integer classifyingBits) {
        this.classifyingBits = classifyingBits;
        return (PostStatusParameters<HAS_CAN_READ, Present>) this;
    }

    @SuppressWarnings("unchecked")
    public PostStatusParameters<Present, HAS_CLASSIFYING_BITS> canRead(boolean canRead) {
        this.canRead = canRead;
        return (PostStatusParameters<Present, HAS_CLASSIFYING_BITS>) this;
    }

    public PostStatusParameters<HAS_CAN_READ, HAS_CLASSIFYING_BITS> detailBits(int detailBits) {
        this.detailBits = detailBits;
        return this;
    }

}
