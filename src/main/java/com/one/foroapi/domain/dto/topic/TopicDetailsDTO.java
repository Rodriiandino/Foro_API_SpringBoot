package com.one.foroapi.domain.dto.topic;

import com.one.foroapi.domain.dto.user.UserSimpleDetailsDTO;
import com.one.foroapi.domain.model.Category;
import com.one.foroapi.domain.model.Topic;

public record TopicDetailsDTO (
        Long id,
        String title,
        String description,
        UserSimpleDetailsDTO user,
        Category category
){
    public TopicDetailsDTO(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getDescription(),
                new UserSimpleDetailsDTO(topic.getUser()),
                topic.getCategory()
        );
    }
}
