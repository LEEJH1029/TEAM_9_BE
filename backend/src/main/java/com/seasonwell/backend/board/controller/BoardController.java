package com.seasonwell.backend.board.controller;

import com.seasonwell.backend.board.dto.BoardRequest;
import com.seasonwell.backend.board.dto.AllBoardResponse;
import com.seasonwell.backend.board.dto.OneBoardResponse;
import com.seasonwell.backend.board.service.BoardService;
import com.seasonwell.backend.global.config.BaseResponse;
import com.seasonwell.backend.global.config.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/community")
@RestController
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 글 등록
    @PostMapping("/write")
    public BaseResponse<String> createBoard(@RequestBody BoardRequest requestDto, HttpSession session) {
        String user = boardService.createBoard(requestDto, session);
        if (user != null) {
            String result = "게시글 생성 완료";
            return new BaseResponse<>(result);
        } else {
            return new BaseResponse<>(ResponseStatus.BAD_REQUEST);
        }
    }

    // 전체 게시글 조회
    @GetMapping("/board")
    public ResponseEntity<List<AllBoardResponse>> getAllBoards() {
        List<AllBoardResponse> allBoardResponses = boardService.findAllBoards();
        if (allBoardResponses.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else {
            return new ResponseEntity<>(allBoardResponses, HttpStatus.OK);
        }
    }

    // 타입별 전체 게시글 조회
    @GetMapping("/{board_type}")
    public ResponseEntity<List<AllBoardResponse>> getAllBoardsByBoardType(@PathVariable Integer board_type) {
        List<AllBoardResponse> allBoardResponses = boardService.findAllBoardsByBoardType(board_type);
        if (allBoardResponses.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else {
            return new ResponseEntity<>(allBoardResponses, HttpStatus.OK);
        }
    }

    // 글 하나 조회
    @GetMapping("/{board_type}/{board_no}")
    public ResponseEntity<OneBoardResponse> getOneBoard(
            @PathVariable Integer board_type,
            @PathVariable Long board_no
    ) {
        OneBoardResponse oneBoardResponse = boardService.findOneBoard(board_type, board_no);
        if (oneBoardResponse != null) {
            return new ResponseEntity<>(oneBoardResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 전체 게시글 검색 - 제목 기준
    @GetMapping("/search/title/all/{board_title}")
    public ResponseEntity<List<AllBoardResponse>> getAllBoardByBoardTitle(
            @PathVariable String board_title
    ) {
        List<AllBoardResponse> allBoardResponses = boardService.findAllBoardByBoardTitle(board_title);
        List<AllBoardResponse> filterResponses = new ArrayList<>();

        for (AllBoardResponse allBoardResponse : allBoardResponses) {
            filterResponses.add(allBoardResponse);
        }
        if (filterResponses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(filterResponses, HttpStatus.OK);
        }
    }

    // 전체 게시글 검색 - 내용 기준
    @GetMapping("/search/content/all/{board_content}")
    public ResponseEntity<List<AllBoardResponse>> getAllBoardByBoardContent(
            @PathVariable String board_content
    ) {
        List<AllBoardResponse> allBoardResponses = boardService.findAllBoardByBoardContent(board_content);
        List<AllBoardResponse> filterResponses = new ArrayList<>();

        for (AllBoardResponse allBoardResponse : allBoardResponses) {
            filterResponses.add(allBoardResponse);
        }
        if (filterResponses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(filterResponses, HttpStatus.OK);
        }
    }

    // 전체 게시글 검색 - 작성자 기준
    @GetMapping("/search/author/author/{board_author}")
    public ResponseEntity<List<AllBoardResponse>> getAllBoardByBoardAuthor(
            @PathVariable Integer board_type,
            @PathVariable String board_author
    ) {
        List<AllBoardResponse> allBoardResponses = boardService.findAllBoardByBoardAuthor(board_author);
        List<AllBoardResponse> filterResponses = new ArrayList<>();

        for (AllBoardResponse allBoardResponse : allBoardResponses) {
            filterResponses.add(allBoardResponse);
        }
        if (filterResponses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(filterResponses, HttpStatus.OK);
        }
    }

    // 타입 내 게시글 검색 - 제목 기준
    @GetMapping("/search/title/{board_type}/{board_title}")
    public ResponseEntity<List<AllBoardResponse>> getAllBoardByBoardTitleAndBoardType(
            @PathVariable Integer board_type,
            @PathVariable String board_title
    ) {
        List<AllBoardResponse> allBoardResponses = boardService.findAllBoardByBoardTitle(board_title);
        List<AllBoardResponse> filterResponses = new ArrayList<>();

        for (AllBoardResponse allBoardResponse : allBoardResponses) {
            if (allBoardResponse.getBoard_type().equals(board_type)) {
                filterResponses.add(allBoardResponse);
            }
        }
        if (filterResponses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(filterResponses, HttpStatus.OK);
        }
    }

    // 타입 내 게시글 검색 - 내용 기준
    @GetMapping("/search/content/{board_type}/{board_content}")
    public ResponseEntity<List<AllBoardResponse>> getAllBoardByBoardContentAndBoardType(
            @PathVariable Integer board_type,
            @PathVariable String board_content
    ) {
        List<AllBoardResponse> allBoardResponses = boardService.findAllBoardByBoardContent(board_content);
        List<AllBoardResponse> filterResponses = new ArrayList<>();

        for (AllBoardResponse allBoardResponse : allBoardResponses) {
            if (allBoardResponse.getBoard_type().equals(board_type)) {
                filterResponses.add(allBoardResponse);
            }
        }
        if (filterResponses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(filterResponses, HttpStatus.OK);
        }
    }

    // 타입 내 게시글 검색 - 작성자 기준
    @GetMapping("/search/author/{board_type}/{board_author}")
    public ResponseEntity<List<AllBoardResponse>> getAllBoardByBoardAuthorAndBoardType(
            @PathVariable Integer board_type,
            @PathVariable String board_author
    ) {
        List<AllBoardResponse> allBoardResponses = boardService.findAllBoardByBoardAuthor(board_author);
        List<AllBoardResponse> filterResponses = new ArrayList<>();

        for (AllBoardResponse allBoardResponse : allBoardResponses) {
            if (allBoardResponse.getBoard_type().equals(board_type)) {
                filterResponses.add(allBoardResponse);
            }
        }
        if (filterResponses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(filterResponses, HttpStatus.OK);
        }
    }
}
