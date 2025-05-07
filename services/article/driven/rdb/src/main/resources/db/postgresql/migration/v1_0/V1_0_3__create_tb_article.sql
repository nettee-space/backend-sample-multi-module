CREATE TABLE IF NOT EXISTS article (
    id          BIGSERIAL,
    title       VARCHAR(255),
    content     TEXT,
    status      VARCHAR(255),
    total_views INTEGER,
    total_likes INTEGER,
    total_shares INTEGER,
    created_at  TIMESTAMP       DEFAULT NOW(),
    updated_at  TIMESTAMP,
    blog_id          BIGINT,


    CONSTRAINT pk_article PRIMARY KEY (id)
    -- CONSTRAINT fk_post_blog FOREIGN KEY (blog_id) REFERENCES blog(id) ON DELETE CASCADE
    );

--테이블 코멘트
COMMENT ON TABLE article IS '아티클';

-- 컬럼 코멘트
COMMENT ON COLUMN article.title       IS '글 제목';
COMMENT ON COLUMN article.content     IS '내용';
COMMENT ON COLUMN article.status      IS '상태';
COMMENT ON COLUMN article.total_views  IS '총 조회수';
COMMENT ON COLUMN article.total_likes  IS '총 좋아요 수';
COMMENT ON COLUMN article.total_shares  IS '총 공유수';
COMMENT ON COLUMN article.created_at  IS '생성시간';
COMMENT ON COLUMN article.updated_at  IS '마지막 수정시간';
COMMENT ON COLUMN article.blog_id  IS '블로그 ID';


