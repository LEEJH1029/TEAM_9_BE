package com.seasonwell.backend.board.controller;

import com.seasonwell.backend.board.dto.BoardRequest;
import com.seasonwell.backend.board.dto.AllBoardResponse;
import com.seasonwell.backend.board.dto.OneBoardResponse;
import com.seasonwell.backend.board.service.BoardService;
import com.seasonwell.backend.global.config.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 글 등록
    @PostMapping("/community/write")
    public BaseResponse<String> createBoard(@RequestBody BoardRequest requestDto) {
        boardService.createBoard(requestDto);
        String result = "게시글 생성 완료";
        return new BaseResponse<>(result);
    }

    // 전체 목록 조회
    @GetMapping("/community")
    public BaseResponse<List<AllBoardResponse>> getAllBoards() {
        List<AllBoardResponse> allBoardResponses = boardService.findAllBoard();
        return new BaseResponse<>(allBoardResponses);
    }

    // 글 하나 조회
    @GetMapping("/community/{board_id}")
    public BaseResponse<OneBoardResponse> getOneBoard(@PathVariable Long board_id) {
        OneBoardResponse oneBoardResponse = boardService.findOneBoard(board_id);
        return new BaseResponse<>(oneBoardResponse);
    }
}
