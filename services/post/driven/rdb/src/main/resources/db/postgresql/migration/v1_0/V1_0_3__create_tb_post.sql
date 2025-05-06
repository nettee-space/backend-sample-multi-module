CREATE TABLE IF NOT EXISTS post (
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


    CONSTRAINT pk_post PRIMARY KEY (id)
    -- CONSTRAINT fk_post_blog FOREIGN KEY (blog_id) REFERENCES blog(id) ON DELETE CASCADE
    );

--테이블 코멘트
COMMENT ON TABLE post IS '포스트';

-- 컬럼 코멘트
COMMENT ON COLUMN post.title       IS '글 제목';
COMMENT ON COLUMN post.content     IS '내용';
COMMENT ON COLUMN post.status      IS '상태';
COMMENT ON COLUMN post.total_views  IS '총 조회수';
COMMENT ON COLUMN post.total_likes  IS '총 좋아요 수';
COMMENT ON COLUMN post.total_shares  IS '총 공유수';
COMMENT ON COLUMN post.created_at  IS '생성시간';
COMMENT ON COLUMN post.updated_at  IS '마지막 수정시간';
COMMENT ON COLUMN post.blog_id  IS '블로그 ID';


