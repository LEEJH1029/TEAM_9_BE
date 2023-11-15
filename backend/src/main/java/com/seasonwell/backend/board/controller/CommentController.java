package com.seasonwell.backend.board.controller;

import com.seasonwell.backend.board.dto.CommentRequest;
import com.seasonwell.backend.board.entity.CommentEntity;
import com.seasonwell.backend.board.service.CommentService;
import com.seasonwell.backend.global.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/community")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{board_type}/{board_no}/comment")
    public BaseResponse<List<CommentEntity>> getAllComments(
            @PathVariable Integer board_type,
            @PathVariable Long board_no
    ) {
        List<CommentEntity> allComments = commentService.getAllComments(board_type, board_no);
        return new BaseResponse<>(allComments);
    }

    @PostMapping("{board_type}/{board_no}/comment/write")
    public BaseResponse<String> writeComment(
            @PathVariable Integer board_type,
            @PathVariable Long board_no,
            @RequestBody CommentRequest commentRequest,
            HttpSession session
    ) {
        commentService.commentWrite(commentRequest, session, board_type, board_no);
        String result = "댓글 작성 완료";
        return new BaseResponse<>(result);
    }
}
