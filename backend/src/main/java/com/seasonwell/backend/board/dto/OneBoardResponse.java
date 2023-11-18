package com.seasonwell.backend.board.dto;

import com.seasonwell.backend.board.entity.Board;
import com.seasonwell.backend.board.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class OneBoardResponse {

    private Long board_no;
    private String board_title;
    private String board_keyword;
    private String board_author;
    private LocalDateTime board_date;
    private String board_type;
    private String board_content;
//    private String disease_code;

    // Comment 목록 추가
    private List<CommentResponse> comments;

    public OneBoardResponse(Board board, List<Comment> comments) {
        this.board_no = board.getBoardNo();
        this.board_title = board.getBoardTitle();
        this.board_keyword = board.getBoardTitle();
        this.board_author = board.getBoardAuthor();
        this.board_date = board.getBoardDate();
        this.board_type = board.getBoardType();
        this.board_content = board.getBoardContent();
//        this.disease_code = board.getDisease().getDisease_code();

        // Comment 추가
        this.comments = comments.stream()
                .map(comment -> new CommentResponse(
                        comment.getBoard().getBoardNo(),
                        comment.getComment_author(),
                        comment.getComment_body(),
                        comment.getCommentDate()
                ))
                .collect(Collectors.toList());
    }
}
