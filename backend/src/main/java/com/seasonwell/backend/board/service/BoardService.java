package com.seasonwell.backend.board.service;


import com.seasonwell.backend.board.dto.AllBoardResponse;
import com.seasonwell.backend.board.dto.BoardRequest;
import com.seasonwell.backend.board.dto.OneBoardResponse;
import com.seasonwell.backend.board.entity.Board;
import com.seasonwell.backend.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;

    // 게시글 생성
    public String createBoard(BoardRequest boardRequest, HttpSession session) {
        Board board = new Board(boardRequest);
        String currentUser = (String) session.getAttribute("userId");

        if (currentUser == null) {
            return null;
        } else {
            board.setBoardAuthor(currentUser);
            boardRepository.save(board);
            return currentUser;
        }
    }

    // 모든 게시글 가져오기
    public List<AllBoardResponse> findAllBoard(Integer board_type) {
        try {
            List<Board> boardList = boardRepository.findAllByBoardType(board_type);
            List<AllBoardResponse> responseDtoList = new ArrayList<>();

            for (Board board : boardList) {
                responseDtoList.add(
                        new AllBoardResponse(board)
                );
            }
            return responseDtoList;
        } catch (Exception e) {
//            throw new ResponseStatus.BAD_REQUEST;
        }
        return null;
    }

    // 게시글 하나 가져오기
    public OneBoardResponse findOneBoard(Integer boardType, Long id) {
        Board board = boardRepository.findByBoardTypeAndBoardNo(boardType, id);
        return new OneBoardResponse(board);
    }

    public List<AllBoardResponse> findAllBoardByBoardTitle(String board_title) {
        try {
            List<Board> boardList = boardRepository.findByBoardTitle(board_title);
            List<AllBoardResponse> responseDtoList = new ArrayList<>();

            for (Board board : boardList) {
                responseDtoList.add(
                        new AllBoardResponse(board)
                );
            }
            return responseDtoList;
        } catch (Exception e) {
//            throw new ResponseStatus.BAD_REQUEST;
        }
        return null;
    }
}
