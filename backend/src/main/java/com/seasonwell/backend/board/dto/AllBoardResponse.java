package com.seasonwell.backend.board.dto;

import com.seasonwell.backend.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AllBoardResponse {
    private Long board_no;
    private String board_title;
    private String board_author;
    private LocalDateTime board_date;
    private Integer board_type;

    public AllBoardResponse(BoardEntity board) {
        this.board_no = board.getBoardNo();
        this.board_title = board.getBoardTitle();
        this.board_author = board.getBoardAuthor();
        this.board_date = board.getBoardDate();
        this.board_type = board.getBoardType();
    }
}
