package nettee.article.type;

import java.util.EnumSet;
import java.util.Set;

public enum DraftStatus {
    DRAFT,
    DONE,
    DELETED,
    PENDING;

    public static final Set<DraftStatus> GENERAL_QUERY_STATUS = EnumSet.of(DRAFT);

    public static Set<DraftStatus> getGeneralQueryStatus() {
        return GENERAL_QUERY_STATUS;
    }
}
