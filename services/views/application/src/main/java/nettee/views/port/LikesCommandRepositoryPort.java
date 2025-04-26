package nettee.views.port;


import nettee.views.Likes;

public interface LikesCommandRepositoryPort {
    void addLike(Likes likes);
    void cancelLikes(Likes likes);
}
