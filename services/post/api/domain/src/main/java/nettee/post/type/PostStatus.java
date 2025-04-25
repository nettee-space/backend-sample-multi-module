package nettee.post.type;

import java.util.EnumSet;
import java.util.Set;

public enum PostStatus {
    ACTIVE,
    SUSPENDED,
    DELETED,
    PENDING;


    public static final Set<PostStatus> GENERAL_QUERY_STATUS = EnumSet.of(ACTIVE, SUSPENDED);

    public static Set<PostStatus> getGeneralQueryStatus() {
        return GENERAL_QUERY_STATUS;
    }
}
