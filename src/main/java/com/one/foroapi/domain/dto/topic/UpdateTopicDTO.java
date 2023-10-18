package com.one.foroapi.domain.dto.topic;

public record UpdateTopicDTO(
        String title,
        String description,
        Long categoryId
) {
}
