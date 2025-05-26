-- 0. 기존 테이블 삭제 (데이터 없음)
DROP TABLE IF EXISTS article.post_view_count;

-- 1. 테이블 생성
CREATE TABLE IF NOT EXISTS article.post_view_count (
    post_id    BIGSERIAL,
    view_count BIGINT DEFAULT 0

    CONSTRAINT pk_post PRIMARY KEY (post_id)
);

-- 2. 테이블 설명
COMMENT ON TABLE article.post_view_count IS '게시글별 조회수 집계 테이블';

-- 3. 컬럼 설명
COMMENT ON COLUMN article.post_view_count.post_id       IS '게시글 ID (PK)';
COMMENT ON COLUMN article.post_view_count.view_count    IS '조회수';
