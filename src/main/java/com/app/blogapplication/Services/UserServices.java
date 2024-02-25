package com.app.blogapplication.Services;


import com.app.blogapplication.model.Author;
import com.app.blogapplication.pojo.AuthorDTO;
import com.app.blogapplication.pojo.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServices {
    AuthorDTO createUser(AuthorDTO user);
    List<AuthorDTO> getALlusers();
    AuthorDTO getUserById(Long userId);
    void deleteUserById(Long userId);
}
