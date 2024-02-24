package com.app.blogapplication.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private int commentId;
    private String comment;
    private Long postId;
//    private Long userId;
//    private PostDTO post;
    private AuthorDTO user;
}
