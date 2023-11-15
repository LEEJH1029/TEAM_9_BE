package com.seasonwell.backend.board.service;

import com.seasonwell.backend.board.dto.CommentRequest;
import com.seasonwell.backend.board.entity.BoardEntity;
import com.seasonwell.backend.board.entity.CommentEntity;
import com.seasonwell.backend.board.repository.BoardRepository;
import com.seasonwell.backend.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public List<CommentEntity> getAllComments(Integer board_type, Long id) {
        BoardEntity board = boardRepository.findByBoardTypeAndBoardNo(board_type, id);
        return commentRepository.findCommentEntitiesByBoard(board);
    }

    @Transactional
    public void commentWrite(CommentRequest commentRequest, HttpSession session, Integer board_type, Long id) {
        BoardEntity board = boardRepository.findByBoardTypeAndBoardNo(board_type, id);
        CommentEntity comment = new CommentEntity(commentRequest, board);
        comment.setComment_author((String) session.getAttribute("userId"));
        commentRepository.save(comment);
    }
}
