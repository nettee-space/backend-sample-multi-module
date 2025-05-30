package nettee.comment.domain.type;

import java.util.EnumSet;
import java.util.Set;

public enum CommentStatus {

    PENDING,
    ACTIVE,
    REMOVED;

    private static final Set<CommentStatus> GENERAL_QUERY_STATUS = EnumSet.of(ACTIVE);

    public static Set<CommentStatus> getGeneralQueryStatus() {
        return GENERAL_QUERY_STATUS;
    }

}
