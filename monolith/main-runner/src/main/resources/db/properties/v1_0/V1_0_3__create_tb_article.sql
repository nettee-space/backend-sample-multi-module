-- 스키마가 없다면 먼저 생성
CREATE SCHEMA IF NOT EXISTS article;

CREATE TABLE IF NOT EXISTS article.article (
    id          BIGSERIAL,
    blog_id     BIGINT,

    title       VARCHAR(255),
    content     TEXT,

    total_views INTEGER,
    total_likes INTEGER,
    total_shares INTEGER,

    status      INTEGER,
    created_at  TIMESTAMP       DEFAULT NOW(),
    updated_at  TIMESTAMP,

    CONSTRAINT pk_article PRIMARY KEY (id)
    -- CONSTRAINT fk_post_blog FOREIGN KEY (blog_id) REFERENCES blog(id) ON DELETE CASCADE
);

--테이블 코멘트
COMMENT ON TABLE article.article IS '포스팅';

-- 컬럼 코멘트
COMMENT ON COLUMN article.article.title         IS '제목';
COMMENT ON COLUMN article.article.content       IS '본문';
COMMENT ON COLUMN article.article.status        IS '상태';
COMMENT ON COLUMN article.article.total_views   IS '총 조회수';
COMMENT ON COLUMN article.article.total_likes   IS '총 좋아요 수';
COMMENT ON COLUMN article.article.total_shares  IS '총 공유수';
COMMENT ON COLUMN article.article.created_at    IS '생성시간';
COMMENT ON COLUMN article.article.updated_at    IS '마지막 수정시간';
COMMENT ON COLUMN article.article.blog_id       IS '블로그 ID';
