package com.one.foroapi.domain.dto.post;

import com.one.foroapi.domain.dto.topic.TopicDetailsDTO;
import com.one.foroapi.domain.dto.user.UserSimpleDetailsDTO;
import com.one.foroapi.domain.model.Post;

public record PostDetailsDTO(
        Long id,
        String title,
        String content,
        UserSimpleDetailsDTO user,
        TopicDetailsDTO topic
) {
    public PostDetailsDTO(Post post) {
        this(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                new UserSimpleDetailsDTO(post.getUser()),
                new TopicDetailsDTO(post.getTopic())
        );
    }
}
