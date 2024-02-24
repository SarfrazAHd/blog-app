package com.app.blogapplication.Services;

import com.app.blogapplication.model.Post;
import com.app.blogapplication.pojo.PostDTO;

import java.util.List;

public interface PostServices {
    PostDTO createPost(PostDTO post);
    List<PostDTO> getAllPosts();
    PostDTO getPostById(Long postId);
    void deletePostById(Long postId);
    PostDTO updatePost(PostDTO post);
}
