package nettee.article.type;

import java.util.EnumSet;
import java.util.Set;

public enum ArticleStatus {
    ACTIVE,
    SUSPENDED,
    DELETED,
    PENDING;

    public static final Set<ArticleStatus> GENERAL_QUERY_STATUS = EnumSet.of(ACTIVE, SUSPENDED);

    public static Set<ArticleStatus> getGeneralQueryStatus() {
        return GENERAL_QUERY_STATUS;
    }
}
