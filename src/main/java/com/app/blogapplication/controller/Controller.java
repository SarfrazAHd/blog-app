package com.app.blogapplication.controller;

import com.app.blogapplication.Services.CategoryService;
import com.app.blogapplication.Services.PostServices;
import com.app.blogapplication.Services.UserServices;
import com.app.blogapplication.pojo.AuthorDTO;
import com.app.blogapplication.pojo.CategoryDTO;
import com.app.blogapplication.pojo.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog-app")
@RequiredArgsConstructor
public class Controller {

    private final UserServices userServices;
    private final PostServices postServices;
    private final CategoryService categoryService;

    @PostMapping("/user/create")
    public ResponseEntity createUser(@RequestBody AuthorDTO user) {
        try {
            user = userServices.createUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/post/create")
    public ResponseEntity createPost(@RequestBody PostDTO post) {
        try {
            PostDTO response = postServices.createPost(post);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/category/create")
    public ResponseEntity createCategory(@RequestBody CategoryDTO category) {
        try {
            if (!categoryService.isCategoryExist(category.getName())) {
                CategoryDTO response = categoryService.createCategory(category);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            return ResponseEntity.ok("Category Already exist");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/posts")
    public ResponseEntity getALlPosts() {
        try {
            List<PostDTO> response = postServices.getALlPosts();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity getPostById(@PathVariable Long postId) {
        try {
            PostDTO response = postServices.getPostById(postId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/categories")
    public ResponseEntity getALlCategory() {
        try {
            List<CategoryDTO> response = categoryService.getALlCategory();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity getCategoryById(@PathVariable Long categoryId) {
        try {
            CategoryDTO response = categoryService.getCategoryById(categoryId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/users")
    public ResponseEntity getALlusers() {
        try {
            List<AuthorDTO> response = userServices.getALlusers();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity getUserById(@PathVariable Long userId) {
        try {
            AuthorDTO response = userServices.getUserById(userId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
