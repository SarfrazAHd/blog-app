package com.app.blogapplication.controller;

import com.app.blogapplication.Services.PostServices;
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

    @DeleteMapping("/{postId}")
    public ResponseEntity deltePostById(@PathVariable Long postId) {
        try {
            postServices.deletePostById(postId);
            return new ResponseEntity<>("post deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*this api having some issue, not fully fuctional..*/
    @PatchMapping("/update")
    public ResponseEntity updatePost(@RequestBody PostDTO post) {
        try {
            PostDTO response = postServices.updatePost(post);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
