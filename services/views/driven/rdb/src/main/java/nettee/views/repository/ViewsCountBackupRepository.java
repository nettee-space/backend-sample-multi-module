package nettee.views.repository;

import nettee.views.entity.ViewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ViewsCountBackupRepository extends JpaRepository<ViewsEntity, Long> {

    @Query("""
            update ViewsEntity v set v.viewCount = :viewCount
            where v.postId = :postId and v.viewCount < :viewCount
           """)
    @Modifying
    int updateViewCount(@Param("postId") Long postId, @Param("viewCount") Long viewCount);
}
