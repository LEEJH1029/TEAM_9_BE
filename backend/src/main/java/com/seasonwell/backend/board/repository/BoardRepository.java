package com.seasonwell.backend.board.repository;

import com.seasonwell.backend.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findAll();
    Optional<BoardEntity> findByBoardNo(Long boardId);
}
