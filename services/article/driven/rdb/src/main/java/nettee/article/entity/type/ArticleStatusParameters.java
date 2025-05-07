package nettee.article.entity.type;

import nettee.article.entity.type.builder.TypeSafeMarkers;
import nettee.article.entity.type.builder.TypeSafeMarkers.Present;
import nettee.article.entity.type.builder.TypeSafeMarkers.Missing;

public class ArticleStatusParameters<
        HAS_CAN_READ extends TypeSafeMarkers,
        HAS_CLASSIFYING_BITS extends TypeSafeMarkers
        > {
    boolean canRead;
    Integer classifyingBits;
    int detailBits;
    private ArticleStatusParameters() {}

    public static ArticleStatusParameters<Missing, Missing> builder() { return new ArticleStatusParameters<>(); }

    public static ArticleStatusParameters<Missing, Missing> generate() {
        return new ArticleStatusParameters<>();
    }

    @SuppressWarnings("unchecked")
    public ArticleStatusParameters<HAS_CAN_READ, Present> classifyingBits(Integer classifyingBits) {
        this.classifyingBits = classifyingBits;
        return (ArticleStatusParameters<HAS_CAN_READ, Present>) this;
    }

    @SuppressWarnings("unchecked")
    public ArticleStatusParameters<Present, HAS_CLASSIFYING_BITS> canRead(boolean canRead) {
        this.canRead = canRead;
        return (ArticleStatusParameters<Present, HAS_CLASSIFYING_BITS>) this;
    }

    public ArticleStatusParameters<HAS_CAN_READ, HAS_CLASSIFYING_BITS> detailBits(int detailBits) {
        this.detailBits = detailBits;
        return this;
    }

}
