package nettee.common.status;

import nettee.common.marker.TypeSafeMarker.Present;

public final class StatusCodeUtil {
    private StatusCodeUtil() {}

    public static int getAsInt(StatusParameters<Present, Present> parameters) {
        return (parameters.generalPurposeBits() << 24)
                | (parameters.systemInfoBits() << 16)
                | (parameters.categoryBits() << 8)
                | parameters.instanceBits();
    }
}
