package com.one.foroapi.domain.service;

import com.one.foroapi.domain.dto.comment.CreateCommentDTO;
import com.one.foroapi.domain.dto.comment.UpdateCommentDTO;
import com.one.foroapi.domain.model.Comment;
import com.one.foroapi.domain.model.Post;
import com.one.foroapi.domain.model.User;
import com.one.foroapi.domain.repository.CommentRepository;
import com.one.foroapi.domain.repository.PostRepository;
import com.one.foroapi.domain.repository.UserRepository;
import com.one.foroapi.infra.exceptions.notEditableException;
import com.one.foroapi.util.TimeLimit;
import jakarta.persistence.EntityNotFoundException;
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
        if (!commentRepository.existsById(commentId)) {
            throw new EntityNotFoundException("Comment not found: " + commentId);
        }
        return commentRepository.findById(commentId).orElse(null);
    }

    public void deleteCommentById(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new EntityNotFoundException("Comment not found: " + commentId);
        }
        commentRepository.deleteById(commentId);
    }

    public Comment updateComment(Long commentId, UpdateCommentDTO updateComment) {
        Comment comment = getCommentById(commentId);
        if (comment != null) {
            if (!TimeLimit.isEditableWithinTimeLimit(comment.getCreated_at())) {
                throw new notEditableException("The comment is not editable after 15 minutes of its creation dat time");
            }
            if (updateComment.content() != null) {
                comment.setContent(updateComment.content());
            }
            return commentRepository.save(comment);
        } else {
            throw new EntityNotFoundException("Comment not found: " + commentId);
        }
    }

}
