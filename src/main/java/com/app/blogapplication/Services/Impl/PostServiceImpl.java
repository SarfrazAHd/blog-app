package com.app.blogapplication.Services.Impl;

import com.app.blogapplication.Services.PostServices;
import com.app.blogapplication.model.Author;
import com.app.blogapplication.model.Category;
import com.app.blogapplication.model.Post;
import com.app.blogapplication.pojo.PostDTO;
import com.app.blogapplication.repository.CategoryRepository;
import com.app.blogapplication.repository.PostRepository;
import com.app.blogapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostServices {
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        try {
            // Post
            Post post = this.modelMapper.map(postDTO, Post.class);

            // Fetching Author by their email address
            Author authorByUserName = userRepository.findAuthorByEmail(postDTO.getAuthor().getEmail());
            // Fetching post category by category name
            Category category = categoryRepository.findByName(postDTO.getCategories().getName());

            post.setAuthor(authorByUserName);
            post.setCategories(category);
            post.setCreatedAt(LocalDateTime.now());

            // Saving post
            Post save = postRepository.save(post);

            log.info("Post :  {} created", post);
            return this.modelMapper.map(save, PostDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<PostDTO> getALlPosts() {
        try {
            List<PostDTO> posts = postRepository.findAll().
                    stream().
                    map(post -> this.modelMapper.map(post, PostDTO.class))
                    .collect(Collectors.toList());
            return posts;
        } catch (Exception e) {
            log.error("Something went wrong while fetching all posts..");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public PostDTO getPostById(Long postId) {
        try {
            PostDTO posts = this.modelMapper.map(postRepository.findById(postId), PostDTO.class);
            return posts;
        } catch (Exception e) {
            log.error("Something went wrong while fetching posts for Id {}",postId);
            throw new RuntimeException(e.getMessage());
        }
    }
}
