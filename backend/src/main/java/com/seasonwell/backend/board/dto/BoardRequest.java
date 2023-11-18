package com.seasonwell.backend.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Lob;


@Getter
@AllArgsConstructor
public class BoardRequest {
    @JsonProperty
    private String board_title;

    @Lob
    @JsonProperty
    private String board_content;

//    @JsonProperty("disease_code")
//    private String disease_code;

    @JsonProperty
    private Integer board_type;


}
