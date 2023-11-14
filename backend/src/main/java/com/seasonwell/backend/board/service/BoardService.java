package com.seasonwell.backend.board.service;


import com.seasonwell.backend.board.dto.AllBoardResponse;
import com.seasonwell.backend.board.dto.BoardRequest;
import com.seasonwell.backend.board.dto.OneBoardResponse;
import com.seasonwell.backend.board.entity.BoardEntity;
import com.seasonwell.backend.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;

    // 게시글 생성
    public void createBoard(BoardRequest boardRequest) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        BoardEntity board = new BoardEntity(boardRequest);
//        board.setBoardAuthor(authentication.getName());
        boardRepository.save(board);
    }

    // 모든 게시글 가져오기
    public List<AllBoardResponse> findAllBoard() {
        try{
            List<BoardEntity> boardList = boardRepository.findAll();

            List<AllBoardResponse> responseDtoList = new ArrayList<>();

            for (BoardEntity board : boardList) {
                responseDtoList.add(
                        new AllBoardResponse(board)
                );
            }
            return responseDtoList;
        } catch (Exception e) {
//            throw new ResponseStatus;
        }
        return null;
    }

    // 게시글 하나 가져오기
    public OneBoardResponse findOneBoard(Long id) {
        BoardEntity board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회 실패")
        );
        return new OneBoardResponse(board);
    }
}
