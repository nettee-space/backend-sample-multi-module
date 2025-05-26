package nettee.views.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "post_view_count")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ViewsEntity {

    @Id
    public Long postId;

    public Long viewCount;
}
