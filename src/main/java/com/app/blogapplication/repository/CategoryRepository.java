package com.app.blogapplication.repository;

import com.app.blogapplication.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String categoryName);
    List<Category> findAllByName(String categoryName);
}
