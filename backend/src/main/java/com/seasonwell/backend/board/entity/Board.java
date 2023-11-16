package com.seasonwell.backend.board.entity;

import com.seasonwell.backend.board.dto.BoardRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Board")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Board extends BoardTime {
    // pk) 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    // fk) 질병 코드
    @Column(length = 100, nullable = false)
    private String diseaseCode;

    // 제목
    @Column(length = 20, nullable = false)
    private String boardTitle;

    // 작성자
    @Column(length = 20)
    private String boardAuthor;

    // 작성글
    @Lob
    @Column
    private String boardContent;

    // 타입
    // 1. 의료상담  2. 의약품 리뷰
    @Column
    private Integer boardType;


    public Board(BoardRequest boardRequest) {
        this.diseaseCode = boardRequest.getDisease_code();
        this.boardTitle = boardRequest.getBoard_title();
        this.boardContent = boardRequest.getBoard_content();
        this.boardType = boardRequest.getBoard_type();
    }

}
