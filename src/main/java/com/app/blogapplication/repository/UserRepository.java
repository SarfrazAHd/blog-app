package com.app.blogapplication.repository;

import com.app.blogapplication.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Author, Long> {
    Author findAuthorByEmail(String email);
    Author findAuthorByUserId(Long id);
}
