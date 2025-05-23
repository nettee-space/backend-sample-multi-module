CREATE TABLE IF NOT EXISTS article.draft (
    id          BIGSERIAL,
    blog_id     BIGINT,
    article_id  BIGINT,

    title       VARCHAR(255),
    content     TEXT,

    status      INTEGER,
    created_at  TIMESTAMP       DEFAULT NOW(),
    updated_at  TIMESTAMP,

    CONSTRAINT pk_draft PRIMARY KEY (id)
    -- CONSTRAINT fk_draft_blog FOREIGN KEY (blog_id) REFERENCES blog(id) ON DELETE CASCADE
    -- CONSTRAINT fk_draft_post FOREIGN KEY (post_id) REFERENCES article(id) ON DELETE CASCADE
);

--테이블 코멘트
COMMENT ON TABLE article.draft IS '임시글';

-- 컬럼 코멘트
COMMENT ON COLUMN article.draft.title       IS '글 제목';
COMMENT ON COLUMN article.draft.content     IS '내용';
COMMENT ON COLUMN article.draft.status      IS '상태';
COMMENT ON COLUMN article.draft.created_at  IS '생성시간';
COMMENT ON COLUMN article.draft.updated_at  IS '마지막 수정시간';
COMMENT ON COLUMN article.draft.blog_id     IS '블로그 ID';
COMMENT ON COLUMN article.draft.article_id  IS '포스트 ID';
