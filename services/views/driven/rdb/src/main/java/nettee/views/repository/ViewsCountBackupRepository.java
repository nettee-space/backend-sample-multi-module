package nettee.views.repository;

import nettee.views.entity.ViewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ViewsCountBackupRepository extends JpaRepository<ViewsEntity, Long> {

    @Query(
            value = "update post_view_count set view_count = :viewCount " +
                    "where post_id = :postId and view_count < :viewCount"
            , nativeQuery = true
    )
    @Modifying
    int updateViewCount(@Param("postId") Long postId, @Param("viewCount") Long viewCount);
}
