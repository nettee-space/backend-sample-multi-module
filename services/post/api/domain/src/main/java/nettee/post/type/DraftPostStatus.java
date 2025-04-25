package nettee.post.type;

import java.util.EnumSet;
import java.util.Set;

public enum DraftPostStatus {
    DRAFT,
    DONE,
    DELETED,
    PENDING;


    public static final Set<DraftPostStatus> GENERAL_QUERY_STATUS = EnumSet.of(DRAFT);

    public static Set<DraftPostStatus> getGeneralQueryStatus() {
        return GENERAL_QUERY_STATUS;
    }
}
