CREATE TABLE IF NOT EXISTS reply (
    id          BIGSERIAL,
    parent_id   BIGSERIAL,
    content     VARCHAR(255)
    status      VARCHAR(255),
    created_at  TIMESTAMP       DEFAULT NOW(),
    updated_at  TIMESTAMP,

    CONSTRAINT pk_comment PRIMARY KEY (id)
);

--테이블 코멘트
COMMENT ON TABLE comment IS '답글';

-- 컬럼 코멘트
COMMENT ON COLUMN comment.parent_id   IS '부모 댓글 ID';
COMMENT ON COLUMN comment.content   IS '내용';
COMMENT ON COLUMN comment.status      IS '상태';
COMMENT ON COLUMN comment.created_at  IS '생성시간';
COMMENT ON COLUMN comment.updated_at  IS '마지막 수정시간';