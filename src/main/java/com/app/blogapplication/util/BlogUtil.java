package com.app.blogapplication.util;

import com.app.blogapplication.model.Post;
import com.app.blogapplication.pojo.PostDTO;
import org.springframework.stereotype.Component;

@Component
public class BlogUtil {
    public PostDTO setPostDtoIntoPost(PostDTO postDTO, PostDTO postFromDB){
        if(postDTO.getTitle()!=null && !postDTO.getTitle().isEmpty()){
            postFromDB.setTitle(postFromDB.getTitle());
        }
        if(postDTO.getContent()!=null && !postDTO.getContent().isEmpty()){
            postFromDB.setContent(postFromDB.getContent());
        }
        if(postDTO.getCategories()!=null ){
            postFromDB.setCategories(postFromDB.getCategories());
        }
        return postFromDB;
    }
}
