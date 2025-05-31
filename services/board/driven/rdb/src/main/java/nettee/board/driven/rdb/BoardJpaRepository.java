package nettee.board.driven.rdb;

import nettee.board.driven.rdb.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> { }