package com.app.blogapplication.pojo;

import com.app.blogapplication.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long categoryId;
    private String name;
//    private Set<PostDTO> posts;
}
