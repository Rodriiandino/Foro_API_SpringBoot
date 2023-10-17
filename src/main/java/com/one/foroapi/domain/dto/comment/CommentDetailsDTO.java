package com.one.foroapi.domain.dto.comment;

import com.one.foroapi.domain.dto.post.PostDetailsDTO;
import com.one.foroapi.domain.dto.user.UserSimpleDetailsDTO;
import com.one.foroapi.domain.model.Comment;

import java.time.LocalDateTime;

public record CommentDetailsDTO(
        long id,
        String content,
        LocalDateTime createdAt,
        UserSimpleDetailsDTO user,
        PostDetailsDTO post

) {
    public CommentDetailsDTO(Comment comment) {
        this(
                comment.getId(),
                comment.getContent(),
                comment.getCreated_at(),
                new UserSimpleDetailsDTO(comment.getUser()),
                new PostDetailsDTO(comment.getPost())

        );
    }
}
