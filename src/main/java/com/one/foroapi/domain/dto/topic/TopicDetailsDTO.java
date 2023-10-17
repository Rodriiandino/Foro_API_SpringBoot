package com.one.foroapi.domain.dto.topic;

import com.one.foroapi.domain.dto.user.UserSimpleDetailsDTO;
import com.one.foroapi.domain.model.Category;
import com.one.foroapi.domain.model.Topic;

import java.time.LocalDateTime;

public record TopicDetailsDTO (
        Long id,
        String title,
        String description,
        LocalDateTime created_at,
        UserSimpleDetailsDTO user,
        Category category
){
    public TopicDetailsDTO(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getDescription(),
                topic.getCreated_at(),
                new UserSimpleDetailsDTO(topic.getUser()),
                topic.getCategory()
        );
    }
}
