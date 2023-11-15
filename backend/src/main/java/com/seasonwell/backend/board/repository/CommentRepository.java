package com.seasonwell.backend.board.repository;

import com.seasonwell.backend.board.entity.BoardEntity;
import com.seasonwell.backend.board.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findCommentEntitiesByBoard(BoardEntity board);
}
