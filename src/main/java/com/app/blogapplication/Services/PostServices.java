package com.app.blogapplication.Services;

import com.app.blogapplication.model.Post;
import com.app.blogapplication.pojo.PostDTO;

import java.util.List;

public interface PostServices {
    //create Post
    PostDTO createPost(PostDTO post);
    List<PostDTO> getALlPosts();

    PostDTO getPostById(Long postId);
    //getALlPost
    //GetOnePost
    //GetPostByCategory
    //GetPostByUser
    //update post
    //delete post
}
