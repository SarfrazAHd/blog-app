package com.app.blogapplication.Services;

import com.app.blogapplication.pojo.CategoryDTO;
import com.app.blogapplication.pojo.PostDTO;

import java.util.List;

public interface CategoryService {
    //create Category
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    boolean isCategoryExist(String categorName);
    List<CategoryDTO> getALlCategory();
    CategoryDTO getCategoryById(Long categoryId);
    void deleteCategory(Long categoryId);
    //delete Category
}
