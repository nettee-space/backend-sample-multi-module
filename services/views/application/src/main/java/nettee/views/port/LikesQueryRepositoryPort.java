package nettee.views.port;


import java.util.List;
import nettee.views.Likes;

public interface LikesQueryRepositoryPort {
    List<Likes> getLikes(Long postId);
}
