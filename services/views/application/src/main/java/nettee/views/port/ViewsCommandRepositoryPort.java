package nettee.views.port;


public interface ViewsCommandRepositoryPort {
    int updateViewCount(Long postId, Long viewCount);

    void save(Long postId, Long viewCount);
}
