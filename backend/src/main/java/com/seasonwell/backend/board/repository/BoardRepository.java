package com.seasonwell.backend.board.repository;

import com.seasonwell.backend.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAll();

    List<Board> findAllByBoardType(Integer boardType);

    Board findByBoardTypeAndBoardNo(Integer boardType, Long boardNo);

    @Query("SELECT m FROM Board m WHERE lower(m.boardTitle) LIKE lower(concat('%', :boardTitle, '%'))")
    List<Board> findByBoardTitle(@Param("boardTitle") String board_title);
}