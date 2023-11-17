package com.seasonwell.backend.board.controller;

import com.seasonwell.backend.board.dto.CommentRequest;
import com.seasonwell.backend.board.dto.CommentResponse;
import com.seasonwell.backend.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/community")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{board_type}/{board_no}/comment")
    public ResponseEntity<List<CommentResponse>> getAllComments(
            @PathVariable Integer board_type,
            @PathVariable Long board_no
    ) {
        List<CommentResponse> allComments = commentService.getAllComments(board_type, board_no);
        if(allComments.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else {
            return new ResponseEntity<>(allComments, HttpStatus.OK);
        }
    }

    @PostMapping("{board_type}/{board_no}/comment/write")
    public ResponseEntity<String> writeComment(
            @PathVariable Integer board_type,
            @PathVariable Long board_no,
            @RequestBody CommentRequest commentRequest,
            HttpSession session
    ) {
        String user = commentService.commentWrite(commentRequest, session, board_type, board_no);
        if(user != null) {
            String result = "댓글 작성 완료";
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
