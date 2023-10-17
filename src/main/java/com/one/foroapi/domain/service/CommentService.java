package com.one.foroapi.domain.service;

import com.one.foroapi.domain.dto.comment.CreateCommentDTO;
import com.one.foroapi.domain.model.Comment;
import com.one.foroapi.domain.model.Post;
import com.one.foroapi.domain.model.User;
import com.one.foroapi.domain.repository.CommentRepository;
import com.one.foroapi.domain.repository.PostRepository;
import com.one.foroapi.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;

    }

    public Comment createComment(CreateCommentDTO createCommentDTO) {
        Comment comment = new Comment(createCommentDTO);

        if (createCommentDTO.postId() != null) {
            Post post = postRepository.findById(createCommentDTO.postId()).orElse(null);
            comment.setPost(post);
        }

        if (createCommentDTO.userId() != null) {
            User user = userRepository.findById(createCommentDTO.userId()).orElse(null);
            comment.setUser(user);
        }

        return commentRepository.save(comment);
    }

    public Page<Comment> getAllComments(Pageable pagination) {
        return commentRepository.findAll(pagination);
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
