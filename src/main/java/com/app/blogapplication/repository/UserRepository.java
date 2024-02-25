package com.app.blogapplication.repository;

import com.app.blogapplication.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Author, Long> {
    Author findAuthorByEmail(String email);
    Author findAuthorByUserId(Long id);

    /*this not working properly, check later.. */
    @Query(nativeQuery = true, value = "delete * from AUTHOR where EMAIL=:username")
    void deleteByEmail(@Param("username") String username);
}
