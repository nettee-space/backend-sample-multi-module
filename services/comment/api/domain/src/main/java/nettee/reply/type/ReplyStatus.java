package nettee.reply.type;

import java.util.EnumSet;
import java.util.Set;

public enum ReplyStatus {

    PENDING,
    ACTIVE,
    REMOVED;

    private static final Set<ReplyStatus> GENERAL_QUERY_STATUS = EnumSet.of(ACTIVE);

    public static Set<ReplyStatus> getGeneralQueryStatus() {
        return GENERAL_QUERY_STATUS;
    }

}
