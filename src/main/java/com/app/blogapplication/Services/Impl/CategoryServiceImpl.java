package com.app.blogapplication.Services.Impl;

import com.app.blogapplication.Services.CategoryService;
import com.app.blogapplication.model.Category;
import com.app.blogapplication.pojo.CategoryDTO;
import com.app.blogapplication.pojo.PostDTO;
import com.app.blogapplication.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        try {
            Category category = categoryRepository.save(this.mapper.map(categoryDTO, Category.class));
            log.info("Category :  {} created", category);
            return this.mapper.map(category, CategoryDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean isCategoryExist(String categorName) {
        List<Category> category = categoryRepository.findAllByName(categorName);
        if (category != null&& !category.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public List<CategoryDTO> getALlCategory() {
        try {
            List<CategoryDTO> categories = categoryRepository.findAll().
                    stream().
                    map(category -> this.mapper.map(category, CategoryDTO.class))
                    .collect(Collectors.toList());
            return categories;
        } catch (Exception e) {
            log.error("Something went wrong while fetching all category..");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {
        try {
            CategoryDTO categories = this.mapper.map(categoryRepository.findById(categoryId),CategoryDTO.class);
            return categories;
        } catch (Exception e) {
            log.error("Something went wrong while fetching categories for ID {}",categoryId);
            throw new RuntimeException(e.getMessage());
        }
    }
}
