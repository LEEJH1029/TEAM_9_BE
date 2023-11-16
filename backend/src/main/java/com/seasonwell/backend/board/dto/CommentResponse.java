package com.seasonwell.backend.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentResponse {
    private Long board_no;
    private String comment_author;
    private String comment_body;
}
