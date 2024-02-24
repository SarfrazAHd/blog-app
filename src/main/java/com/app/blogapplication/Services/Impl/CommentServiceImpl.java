package com.app.blogapplication.Services.Impl;

import com.app.blogapplication.Services.CommentService;
import com.app.blogapplication.model.Author;
import com.app.blogapplication.model.Comment;
import com.app.blogapplication.model.Post;
import com.app.blogapplication.pojo.CommentDTO;
import com.app.blogapplication.repository.CommentRepository;
import com.app.blogapplication.repository.PostRepository;
import com.app.blogapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO) {
        try{
            Post post = postRepository.findPostByPostId(commentDTO.getPostId());
            Author user = userRepository.findAuthorByUserId(commentDTO.getUser().getUserId());
            Comment map = modelMapper.map(commentDTO, Comment.class);
            map.setPost(post);
            map.setUser(user);
            Comment response = commentRepository.save(map);
            log.info("Comment created: {}, for postId {}, by userId {}", response, commentDTO.getPostId()
                    , commentDTO.getUser().getUserId());

            return modelMapper.map(response,CommentDTO.class);
        }catch (Exception e){
            log.error("Comment creation failed due to {}", e.getCause());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<CommentDTO> getAllCommentByPostId(Long postId) {
        try {
            List<Comment> commentByPostId = commentRepository.findAllCommentByPostId(postId);
            List<CommentDTO> comments = commentByPostId.stream()
                    .map(comment -> modelMapper.map(comment, CommentDTO.class))
                    .collect(Collectors.toList());
            log.info("Fetched list of comment by postId {}",comments);
            return comments;
        } catch (Exception e) {
            log.error("getAllCOmmentByPostId failed due to {}", e.getCause());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<CommentDTO> getAllCommentByUserId(Long userId) {
        try {
            List<Comment> commentByUserId = commentRepository.findAllCommentByUserId(userId);
            List<CommentDTO> comments = commentByUserId.stream()
                    .map(comment -> modelMapper.map(comment, CommentDTO.class))
                    .collect(Collectors.toList());
            log.info("Fetched list of comment by userId {}",comments);
            return comments;
        } catch (Exception e) {
            log.error("getAllCommentByUserId failed due to {}", e.getCause());
            throw new RuntimeException(e.getMessage());
        }
    }
}
