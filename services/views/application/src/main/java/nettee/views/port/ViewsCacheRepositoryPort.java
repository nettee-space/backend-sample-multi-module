package nettee.views.port;


import nettee.views.Views;

// redis
public interface ViewsCacheRepositoryPort {
    Long increase(Long postId);

    boolean getLock(Views views, java.time.Duration ttl);
}
