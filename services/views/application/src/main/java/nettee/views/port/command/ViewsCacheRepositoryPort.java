package nettee.views.port.command;


// redis
public interface ViewsCacheRepositoryPort {
    void increase(Long postId, Long userId);
}
