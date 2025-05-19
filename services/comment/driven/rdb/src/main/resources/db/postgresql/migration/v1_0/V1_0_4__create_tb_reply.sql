CREATE TABLE IF NOT EXISTS reply (
    id          BIGSERIAL,
    comment_id  BIGINT,
    content     VARCHAR(255),
    status      VARCHAR(255),
    created_at  TIMESTAMP       DEFAULT NOW(),
    updated_at  TIMESTAMP,

    CONSTRAINT pk_reply PRIMARY KEY (id)
);

--테이블 코멘트
COMMENT ON TABLE reply IS '답글';

-- 컬럼 코멘트
COMMENT ON COLUMN reply.comment_id   IS '부모 댓글 ID';
COMMENT ON COLUMN reply.content   IS '내용';
COMMENT ON COLUMN reply.status      IS '상태';
COMMENT ON COLUMN reply.created_at  IS '생성시간';
COMMENT ON COLUMN reply.updated_at  IS '마지막 수정시간';