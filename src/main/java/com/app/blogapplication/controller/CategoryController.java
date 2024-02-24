package com.app.blogapplication.controller;

import com.app.blogapplication.Services.CategoryService;
import com.app.blogapplication.pojo.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/blog-app/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
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

    @GetMapping("/all")
    public ResponseEntity getALlCategory() {
        try {
            List<CategoryDTO> response = categoryService.getALlCategory();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity getCategoryById(@PathVariable Long categoryId) {
        try {
            CategoryDTO response = categoryService.getCategoryById(categoryId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
