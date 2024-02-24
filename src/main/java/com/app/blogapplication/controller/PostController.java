package com.app.blogapplication.controller;

import com.app.blogapplication.Services.CategoryService;
import com.app.blogapplication.Services.CommentService;
import com.app.blogapplication.Services.PostServices;
import com.app.blogapplication.Services.UserServices;
import com.app.blogapplication.pojo.AuthorDTO;
import com.app.blogapplication.pojo.CategoryDTO;
import com.app.blogapplication.pojo.CommentDTO;
import com.app.blogapplication.pojo.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog-app/post")
@RequiredArgsConstructor
public class PostController {

    private final PostServices postServices;

    @PostMapping("/create")
    public ResponseEntity createPost(@RequestBody PostDTO post) {
        try {
            PostDTO response = postServices.createPost(post);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/all")
    public ResponseEntity getALlPosts() {
        try {
            List<PostDTO> response = postServices.getAllPosts();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{postId}")
    public ResponseEntity getPostById(@PathVariable Long postId) {
        try {
            PostDTO response = postServices.getPostById(postId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
