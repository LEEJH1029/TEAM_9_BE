package com.seasonwell.backend.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentRequest {
    @JsonProperty
    private String comment_body;

    @JsonProperty
    private Long boardNo;
}
