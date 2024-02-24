package com.app.blogapplication.repository;

import com.app.blogapplication.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(nativeQuery = true, value = "select * from COMMENT where POST_ID=:postId")
    List<Comment> findAllCommentByPostId(@Param("postId") Long postId);

    @Query(nativeQuery = true, value = "select * from COMMENT where USER_ID=:userId")
    List<Comment> findAllCommentByUserId(@Param("userId") Long userId);
}
