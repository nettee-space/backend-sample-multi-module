package nettee.common.status;

import nettee.common.marker.TypeSafeMarker.Missing;
import nettee.common.status.CustomStatusParameters.LongGeneralPurposeFeatures;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

import static nettee.common.status.exception.StatusCodeErrorCode.GP_BITS_NOT_DISTINCT;
import static nettee.common.status.exception.StatusCodeErrorCode.GP_BITS_OUT_OF_BOUND;
import static nettee.common.status.exception.StatusCodeErrorCode.GP_CUSTOM_KEY_NOT_CORRECT;
import static nettee.common.status.exception.StatusCodeErrorCode.TOTAL_BITS_OVERFLOW;

public final class CustomStatusParametersSupplier implements Supplier<CustomStatusParameters<Missing, Missing>> {
    private final int sysInfoSize;
    private final int categorySize;
    private final int instanceDetailSize;
    private final LongGeneralPurposeFeaturesValue featuresBits;

    public CustomStatusParametersSupplier(
            int generalPurposeBitSize,
            int systemInfoBitSize,
            int categoryBitSize,
            int instanceBitSize,
            LongGeneralPurposeFeaturesValue featuresBits
    ) {
        // validate the total size
        int totalSize = generalPurposeBitSize + systemInfoBitSize + categoryBitSize + instanceBitSize;
        if (totalSize > Long.BYTES * 8) {
            throw TOTAL_BITS_OVERFLOW.exception();
        }

        // validate that the GP list fits within the GP size.
        featuresBits.validateSize(generalPurposeBitSize);

        this.sysInfoSize = systemInfoBitSize;
        this.categorySize = categoryBitSize;
        this.instanceDetailSize = instanceBitSize;
        this.featuresBits = featuresBits;
    }

    public int systemInfoBitSize() {
        return sysInfoSize;
    }

    public int categoryBitSize() {
        return categorySize;
    }

    public int instanceDetailBitSize() {
        return instanceDetailSize;
    }

    public LongGeneralPurposeFeaturesValue features() {
        return featuresBits;
    }

    @Override
    public CustomStatusParameters<Missing, Missing> get() {
        return new CustomStatusParameters<>(this);
    }

    /**
     * 일반 목적 기능(GP features)을 독립된 비트 영역으로 표현하기 위한 클래스입니다.
     */
    public static final class LongGeneralPurposeFeaturesValue {
        final private long read;
        final private long update;
        final private long subItemRead;
        final private long subItemUpdate;
        final private Map<String, Long> extended;
        final private long max;

        /**
         * @param read '일반 조회 목적'을 표현할 비트를 입력합니다.
         * @param update '일반 수정 목적'을 표현할 비트를 입력합니다.
         * @param subItemRead '하위항목 일반 조회 목적'을 표현할 비트를 입력합니다.
         * @param subItemUpdate '하위항목 일반 수정 목적'을 표현할 비트를 입력합니다.
         * @param extended 그 외 확장된 목적의 비트를 map 구조로 전달합니다. (Key: String, Value: Long)
         */
        public LongGeneralPurposeFeaturesValue(
                long read,
                long update,
                long subItemRead,
                long subItemUpdate,
                Map<String, Long> extended
        ) {
            long bitsOverlapped = read | update | subItemRead | subItemUpdate;
            long bitsSum = read + update + subItemRead + subItemUpdate;

            if (bitsOverlapped != bitsSum) {
                throw GP_BITS_NOT_DISTINCT.exception();
            }

            if (extended != null && !extended.isEmpty()) {
                for (var value : extended.values()) {
                    bitsOverlapped |= value;
                    bitsSum += value;
                    if (bitsOverlapped != bitsSum) {
                        throw GP_BITS_NOT_DISTINCT.exception();
                    }
                }
            }

            this.read = read;
            this.update = update;
            this.subItemRead = subItemRead;
            this.subItemUpdate = subItemUpdate;
            this.extended = extended;
            this.max = bitsOverlapped;
        }

        public long read() {
            return read;
        }

        public long update() {
            return update;
        }

        public long subItemRead() {
            return subItemRead;
        }

        public long subItemUpdate() {
            return subItemUpdate;
        }

        public Map<String, Long> extended() {
            return extended;
        }

        public long max() {
            return max;
        }

        public long getValueOf(Collection<String> extendedKeys, LongGeneralPurposeFeatures... features) {
            long value = 0;

            for (var extendedKey : extendedKeys) {
                if (!extended.containsKey(extendedKey)) {
                    throw GP_CUSTOM_KEY_NOT_CORRECT.exception();
                }

                value |= extended.get(extendedKey);
            }

            value |= getValueOf(features);

            return value;
        }

        public long getValueOf(LongGeneralPurposeFeatures... features) {
            long value = 0;

            for (var feature : features) {
                if (feature == LongGeneralPurposeFeatures.ALL || value == max) {
                    return max;
                }

                value |= switch (feature) {
                    case READ -> read;
                    case UPDATE -> update;
                    case SUBITEM_READ -> subItemRead;
                    case SUBITEM_UPDATE -> subItemUpdate;
                    default -> throw new Error("");
                };
            }

            return value;
        }

        public void validateSize(int size) {
            if (max >= (1L << size)) {
                throw GP_BITS_OUT_OF_BOUND.exception();
            }
        }
    }
}
