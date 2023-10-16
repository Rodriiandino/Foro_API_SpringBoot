package com.one.foroapi.domain.dto.comment;

import com.one.foroapi.domain.dto.post.PostDetailsDTO;
import com.one.foroapi.domain.dto.user.UserSimpleDetailsDTO;
import com.one.foroapi.domain.model.Comment;

public record CommentDetailsDTO(
        long id,
        String content,
        UserSimpleDetailsDTO user,
        PostDetailsDTO post
) {
    public CommentDetailsDTO(Comment comment) {
        this(
                comment.getId(),
                comment.getContent(),
                new UserSimpleDetailsDTO(comment.getUser()),
                new PostDetailsDTO(comment.getPost())
        );
    }
}
