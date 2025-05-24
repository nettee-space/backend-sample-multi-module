-- 더미 데이터 삽입 (post_id = 1)
INSERT INTO article.post_view_count (post_id, view_count)
VALUES (1, 0)
    ON CONFLICT (post_id) DO NOTHING;
