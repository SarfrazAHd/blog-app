package com.app.blogapplication.Services;

import com.app.blogapplication.model.Comment;
import com.app.blogapplication.pojo.CommentDTO;

import java.util.List;

public interface CommentService {

    //create comment
    CommentDTO createComment(CommentDTO commentDTO);

    // getAllCommentByPostId
    List<CommentDTO> getAllCommentByPostId(Long postId);
    // getAllCommentsByUserId
    List<CommentDTO> getAllCommentByUserId(Long userId);
}
