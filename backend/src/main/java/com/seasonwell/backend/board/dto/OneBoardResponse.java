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
public class OneBoardResponse {

    private Long board_no;
    private String board_title;
    private String board_author;
    private LocalDateTime board_date;
    private Integer board_type;
    private String board_content;
    private String disease_code;

    public OneBoardResponse(BoardEntity board) {
        this.board_no = board.getBoardNo();
        this.board_title = board.getBoardTitle();
        this.board_author = board.getBoardAuthor();
        this.board_date = board.getBoardDate();
        this.board_type = board.getBoardType();
        this.board_content = board.getBoardContent();
        this.disease_code = board.getDiseaseCode();
    }
}
