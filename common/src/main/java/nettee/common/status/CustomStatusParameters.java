package nettee.common.status;

import nettee.common.marker.TypeSafeMarker;
import nettee.common.marker.TypeSafeMarker.Missing;
import nettee.common.marker.TypeSafeMarker.Present;
import nettee.common.status.CustomStatusParametersSupplier.LongGeneralPurposeFeaturesValue;

import java.util.Collection;

import static nettee.common.status.exception.StatusCodeErrorCode.CATEGORY_BITS_OVERFLOW;
import static nettee.common.status.exception.StatusCodeErrorCode.INSTANCE_DETAIL_BITS_OVERFLOW;
import static nettee.common.status.exception.StatusCodeErrorCode.SYS_INFO_OVERFLOW;

public class CustomStatusParameters<
        C extends TypeSafeMarker,
        I extends TypeSafeMarker
> {
//    private final long gpMax;
    private final long sysInfoMax;
    private final long categoryMax;
    private final long instanceDetailMax;
    private final LongGeneralPurposeFeaturesValue featuresBits;

    private long generalPurposeBits;
    private long systemInfoBits;

    private long categoryBits;
    private long instanceBits;

    CustomStatusParameters(CustomStatusParametersSupplier supplier) {
//        this.gpMax = (1L << supplier.generalPurposeBitSize()) - 1;
        this.sysInfoMax = (1L << supplier.systemInfoBitSize()) - 1;
        this.categoryMax = (1L << supplier.categoryBitSize()) - 1;
        this.instanceDetailMax = (1L << supplier.instanceDetailBitSize()) - 1;
        this.featuresBits = supplier.features();
    }

    public static CustomStatusParameters<Missing, Missing> generateWith(CustomStatusParametersSupplier supplier) {
        return new CustomStatusParameters<>(supplier);
    }

    public CustomStatusParameters<C, I> generalPurposeFeatures(
            Collection<String> extendedFeatures,
            LongGeneralPurposeFeatures... features
    ) {
        generalPurposeBits = featuresBits.getValueOf(extendedFeatures, features);
        return this;
    }

    public CustomStatusParameters<C, I> generalPurposeFeatures(LongGeneralPurposeFeatures... features) {
        generalPurposeBits = featuresBits.getValueOf(features);
        return this;
    }

    public CustomStatusParameters<C, I> systemInfoBits(long systemInfoBits) {
        if (systemInfoBits > sysInfoMax) {
            throw SYS_INFO_OVERFLOW.exception();
        }
        this.systemInfoBits = systemInfoBits;
        return this;
    }

    public CustomStatusParameters<Present, I> categoryBits(long categoryBits) {
        if (categoryBits > categoryMax) {
            throw CATEGORY_BITS_OVERFLOW.exception();
        }
        this.categoryBits = categoryBits;
        @SuppressWarnings("unchecked")
        var instance = (CustomStatusParameters<Present, I>) this;
        return instance;
    }

    public CustomStatusParameters<C, Present> instanceBits(long instanceBits) {
        if (instanceBits > instanceDetailMax) {
            throw INSTANCE_DETAIL_BITS_OVERFLOW.exception();
        }
        this.instanceBits = instanceBits;
        @SuppressWarnings("unchecked")
        var instance = (CustomStatusParameters<C, Present>) this;
        return instance;
    }

    public long generalPurposeBits() {
        return generalPurposeBits;
    }

    public long systemInfoBits() {
        return systemInfoBits;
    }

    public long categoryBits() {
        return categoryBits;
    }

    public long instanceBits() {
        return instanceBits;
    }

    public enum LongGeneralPurposeFeatures {
        ALL,
        READ,
        UPDATE,
        SUBITEM_READ,
        SUBITEM_UPDATE
    }
}