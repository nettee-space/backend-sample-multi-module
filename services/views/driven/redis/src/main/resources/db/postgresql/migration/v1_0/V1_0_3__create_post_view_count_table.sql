-- 1. 테이블 생성
CREATE TABLE IF NOT EXISTS post_view_count (
                                               post_id    BIGINT PRIMARY KEY,
                                               view_count BIGINT NOT NULL DEFAULT 0
);

-- 2. 테이블 설명
COMMENT ON TABLE post_view_count IS '게시글별 조회수 집계 테이블';

-- 3. 컬럼 설명
COMMENT ON COLUMN post_view_count.post_id IS '게시글 ID (PK)';
COMMENT ON COLUMN post_view_count.view_count IS '조회수';

-- 4. 초기 데이터 삽입 (post_id = 1)
INSERT INTO post_view_count (post_id, view_count)
VALUES (1, 0)
    ON CONFLICT (post_id) DO NOTHING;
