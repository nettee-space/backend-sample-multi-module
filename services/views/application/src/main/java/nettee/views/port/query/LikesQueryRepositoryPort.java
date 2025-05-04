package nettee.views.port.query;


import java.util.List;
import nettee.views.Likes;

public interface LikesQueryRepositoryPort {
    List<Likes> getLikes(Long postId);
}
