CREATE TABLE IF NOT EXISTS article.comment (
    id          BIGSERIAL,
    board_id    BIGINT,

    content     VARCHAR(255),

    status      INTEGER,
    created_at  TIMESTAMP       DEFAULT NOW(),
    updated_at  TIMESTAMP,

    CONSTRAINT pk_comment PRIMARY KEY (id)
);

--테이블 코멘트
COMMENT ON TABLE article.comment IS '댓글';

-- 컬럼 코멘트
COMMENT ON COLUMN article.comment.content       IS '내용';
COMMENT ON COLUMN article.comment.board_id      IS '게시물 ID';
COMMENT ON COLUMN article.comment.status        IS '상태';
COMMENT ON COLUMN article.comment.created_at    IS '생성시간';
COMMENT ON COLUMN article.comment.updated_at    IS '마지막 수정시간';
