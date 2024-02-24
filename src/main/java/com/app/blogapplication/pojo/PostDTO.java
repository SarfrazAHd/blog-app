package com.app.blogapplication.pojo;

import com.app.blogapplication.model.Author;
import com.app.blogapplication.model.Category;
import com.app.blogapplication.model.Comment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long postId;
    private String title;
    private String content;
    private AuthorDTO author;
    private CategoryDTO categories;
    private List<CommentDTO> comments;
    private LocalDateTime createdAt;
}