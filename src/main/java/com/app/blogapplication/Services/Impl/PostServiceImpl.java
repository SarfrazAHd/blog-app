package com.app.blogapplication.Services.Impl;

import com.app.blogapplication.Services.PostServices;
import com.app.blogapplication.model.Author;
import com.app.blogapplication.model.Category;
import com.app.blogapplication.model.Comment;
import com.app.blogapplication.model.Post;
import com.app.blogapplication.pojo.CommentDTO;
import com.app.blogapplication.pojo.PostDTO;
import com.app.blogapplication.repository.CategoryRepository;
import com.app.blogapplication.repository.CommentRepository;
import com.app.blogapplication.repository.PostRepository;
import com.app.blogapplication.repository.UserRepository;
import com.app.blogapplication.util.BlogUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostServices {
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;
    private final BlogUtil blogUtil;
    @Override
    public PostDTO createPost(PostDTO postDTO) {
        try {
            Post post = this.modelMapper.map(postDTO, Post.class);
            Author authorByUserName = userRepository.findAuthorByEmail(postDTO.getAuthor().getEmail());
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
    public List<PostDTO> getAllPosts() {
        try {
            List<PostDTO> posts = postRepository.findAll().
                    stream()
                    .map(post -> this.modelMapper.map(post, PostDTO.class))
                    .map(post -> {
                        List<CommentDTO> comments = commentRepository
                                .findAllCommentByPostId(post.getPostId()).stream()
                                .map(comment -> modelMapper.map(comment, CommentDTO.class))
                                .collect(Collectors.toList());
                        post.setComments(comments);
                        return post;
                    })
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
            List<CommentDTO> commentsByPostId = commentRepository.findAllCommentByPostId(postId)
                    .stream()
                    .map(comment -> modelMapper.map(comment, CommentDTO.class))
                    .collect(Collectors.toList());
            posts.setComments(commentsByPostId);
            return posts;

        } catch (Exception e) {
            log.error("Something went wrong while fetching posts for Id {}", postId);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deletePostById(Long postId) {
        try {
            postRepository.deleteById(postId);
        } catch (Exception e) {
            log.error("something  went wrong while deleting postId : {}", postId);
            e.printStackTrace();
        }
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO) {
        try {
//            Post postFromDb = postRepository.findPostByPostId(postDTO.getPostId());
            PostDTO posts = this.modelMapper.map(postRepository.findById(postDTO.getPostId()), PostDTO.class);
            posts = blogUtil.setPostDtoIntoPost(postDTO, posts);

            Post save = postRepository.save(modelMapper.map(posts, Post.class));

            return modelMapper.map(save, PostDTO.class);
        } catch (Exception e) {
            log.error("something  went wrong while update postId : {}", postDTO.getPostId());
            throw new RuntimeException(e.getMessage());
        }
    }
}
