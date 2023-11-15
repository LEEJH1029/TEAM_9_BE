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
    public List<CommentEntity> getAllComments(Long id) {
        BoardEntity board = boardRepository.findByBoardNo(id).get();
        return commentRepository.findCommentEntitiesByBoard(board);
    }

    @Transactional
    public void commentWrite(CommentRequest commentRequest, HttpSession session, Long id) {
        BoardEntity board = boardRepository.findByBoardNo(id).orElseThrow(()->new IllegalArgumentException("조회 실패"));
        CommentEntity comment = new CommentEntity(commentRequest, board);
        comment.setComment_author((String) session.getAttribute("userId"));
        commentRepository.save(comment);
    }
}
