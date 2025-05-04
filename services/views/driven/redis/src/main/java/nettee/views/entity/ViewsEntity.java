package nettee.views.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "post_view_count")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ViewsEntity {

    @Id
    private Long postId;

    private Long viewCount;
}
