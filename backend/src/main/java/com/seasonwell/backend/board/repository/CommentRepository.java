package com.seasonwell.backend.board.repository;

import com.seasonwell.backend.board.entity.Board;
import com.seasonwell.backend.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentEntitiesByBoard(Board board);
}
