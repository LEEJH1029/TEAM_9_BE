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


    @GetMapping("/{board_id}/comment")
    public BaseResponse<List<CommentEntity>> getAllComments(@PathVariable Long board_id) {
        List<CommentEntity> allComments = commentService.getAllComments(board_id);
        return new BaseResponse<>(allComments);
    }

    @PostMapping("/{board_id}/comment/write")
    public BaseResponse<String> writeComment(@RequestBody CommentRequest commentRequest, HttpSession session, @PathVariable Long board_id) {
        commentService.commentWrite(commentRequest, session, board_id);
        String result = "댓글 작성 완료";
        return new BaseResponse<>(result);
    }
}
