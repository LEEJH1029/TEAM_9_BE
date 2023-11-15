package com.seasonwell.backend.board.entity;

import com.seasonwell.backend.board.dto.CommentRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long comment_id;

    @Column(nullable = false)
    private String comment_author;

    @Column(nullable = false)
    @Lob
    private String comment_body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardNo")
    private BoardEntity board;

    public CommentEntity(CommentRequest commentRequest, BoardEntity board) {
        this.comment_body = commentRequest.getComment_body();
//        this.boardId = requestDto.getBoardId();
        this.board = board;
    }
}
