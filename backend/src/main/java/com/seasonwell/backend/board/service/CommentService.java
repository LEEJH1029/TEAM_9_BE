package com.seasonwell.backend.board.service;

import com.seasonwell.backend.board.dto.CommentRequest;
import com.seasonwell.backend.board.dto.CommentResponse;
import com.seasonwell.backend.board.entity.BoardEntity;
import com.seasonwell.backend.board.entity.CommentEntity;
import com.seasonwell.backend.board.repository.BoardRepository;
import com.seasonwell.backend.board.repository.CommentRepository;
import com.seasonwell.backend.user.entity.UserEntity;
import com.seasonwell.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<CommentResponse> getAllComments(Integer board_type, Long id) {
        BoardEntity board = boardRepository.findByBoardTypeAndBoardNo(board_type, id);
        List<CommentEntity> commentsInfo = commentRepository.findCommentEntitiesByBoard(board);

        List<CommentResponse> comments = new ArrayList<>();

        for (CommentEntity comment : commentsInfo) {
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
        String userId = (String) session.getAttribute("userId");

        Optional<UserEntity> currentUser = userRepository.findByUserId(userId);

        Boolean authority = true;

        if (board.getBoardType() == 1) {    // 의료 상담 게시판일 경우
            if (currentUser.get().getUser_type() != 2) {    // 의료 종사자일 경우
                authority = false;
            }
        }

        if (authority) {
            CommentEntity comment = new CommentEntity(commentRequest, board);


            if (userId != null) {
                comment.setComment_author(userId);
                commentRepository.save(comment);
                return userId;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
