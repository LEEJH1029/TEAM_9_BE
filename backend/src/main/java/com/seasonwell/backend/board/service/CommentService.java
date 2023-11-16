package com.seasonwell.backend.board.service;

import com.seasonwell.backend.board.dto.CommentRequest;
import com.seasonwell.backend.board.dto.CommentResponse;
import com.seasonwell.backend.board.entity.BoardEntity;
import com.seasonwell.backend.board.entity.CommentEntity;
import com.seasonwell.backend.board.repository.BoardRepository;
import com.seasonwell.backend.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public List<CommentResponse> getAllComments(Integer board_type, Long id) {
        BoardEntity board = boardRepository.findByBoardTypeAndBoardNo(board_type, id);
        List<CommentEntity> commentsInfo = commentRepository.findCommentEntitiesByBoard(board);

        List<CommentResponse> comments = new ArrayList<>();

        for(CommentEntity comment : commentsInfo) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setBoard_no(board.getBoardNo());
            commentResponse.setComment_author(comment.getComment_author());
            commentResponse.setComment_body(comment.getComment_body());

            comments.add(commentResponse);
        }

        return comments;
    }

    @Transactional
    public String commentWrite(CommentRequest commentRequest, HttpSession session, Integer board_type, Long id) {
        BoardEntity board = boardRepository.findByBoardTypeAndBoardNo(board_type, id);
        CommentEntity comment = new CommentEntity(commentRequest, board);

        String user = (String) session.getAttribute("userId");

        if (user != null) {
            comment.setComment_author(user);
            commentRepository.save(comment);
            return user;
        } else {
            return null;
        }
    }
}
