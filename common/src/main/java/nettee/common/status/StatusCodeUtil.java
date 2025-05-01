package nettee.common.status;

import nettee.common.marker.TypeSafeMarker.Present;

import static nettee.common.status.StatusCodeConstants.Default.CATEGORY_SHIFT;
import static nettee.common.status.StatusCodeConstants.Default.GENERAL_PURPOSE_SHIFT;
import static nettee.common.status.StatusCodeConstants.Default.SYSTEM_INFORMATION_SHIFT;

public final class StatusCodeUtil {
    private StatusCodeUtil() {}

    public static int getAsInt(StatusParameters<Present, Present> parameters) {
        return (parameters.generalPurposeBits() << GENERAL_PURPOSE_SHIFT)
                | (parameters.systemInfoBits() << SYSTEM_INFORMATION_SHIFT)
                | (parameters.categoryBits() << CATEGORY_SHIFT)
                | parameters.instanceBits();
    }

    // TODO
//    public static long getAsLong(CustomStatusParameters<Present, Present> parameters) {
//        // ...
//    }
}
