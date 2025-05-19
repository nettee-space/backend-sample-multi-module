CREATE TABLE IF NOT EXISTS comment (
    id          BIGSERIAL,
    board_id    BIGINT,
    content     VARCHAR(255),
    status      VARCHAR(255),
    created_at  TIMESTAMP       DEFAULT NOW(),
    updated_at  TIMESTAMP,

    CONSTRAINT pk_comment PRIMARY KEY (id)
);

--테이블 코멘트
COMMENT ON TABLE comment IS '댓글';

-- 컬럼 코멘트
COMMENT ON COLUMN comment.content   IS '내용';
COMMENT ON COLUMN comment.board_id    IS '게시물 ID';
COMMENT ON COLUMN comment.status      IS '상태';
COMMENT ON COLUMN comment.created_at  IS '생성시간';
COMMENT ON COLUMN comment.updated_at  IS '마지막 수정시간';