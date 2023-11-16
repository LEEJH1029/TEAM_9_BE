package com.seasonwell.backend.board.repository;

import com.seasonwell.backend.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findAll();

    List<BoardEntity> findAllByBoardType(Integer boardType);

    BoardEntity findByBoardTypeAndBoardNo(Integer boardType, Long boardNo);

    @Query("SELECT m FROM Board m WHERE lower(m.boardTitle) LIKE lower(concat('%', :boardTitle, '%'))")
    List<BoardEntity> findByBoardTitle(@Param("boardTitle") String board_title);
}