package nettee.views.port;


// redis
public interface ViewsCacheRepositoryPort {
    void increase(Long postId, Long userId);
}
