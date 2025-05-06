CREATE TABLE IF NOT EXISTS draft (
    id          BIGSERIAL,
    title       VARCHAR(255),
    content     TEXT,
    status      VARCHAR(255),
    created_at  TIMESTAMP       DEFAULT NOW(),
    updated_at  TIMESTAMP,
    blog_id      BIGINT,
    post_id      BIGINT,

    CONSTRAINT pk_draft PRIMARY KEY (id)
    -- CONSTRAINT fk_draft_blog FOREIGN KEY (blog_id) REFERENCES blog(id) ON DELETE CASCADE
    -- CONSTRAINT fk_draft_post FOREIGN KEY (post_id) REFERENCES post(id) ON DELETE CASCADE
);

--테이블 코멘트
COMMENT ON TABLE draft IS '임시글';

-- 컬럼 코멘트
COMMENT ON COLUMN draft.title       IS '글 제목';
COMMENT ON COLUMN draft.content     IS '내용';
COMMENT ON COLUMN draft.status      IS '상태';
COMMENT ON COLUMN draft.created_at  IS '생성시간';
COMMENT ON COLUMN draft.updated_at  IS '마지막 수정시간';
COMMENT ON COLUMN draft.blog_id  IS '블로그 ID';
COMMENT ON COLUMN draft.post_id  IS '포스트 ID';


