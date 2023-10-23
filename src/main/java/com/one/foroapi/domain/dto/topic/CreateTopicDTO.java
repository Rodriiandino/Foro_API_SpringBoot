package com.one.foroapi.domain.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTopicDTO(
        @NotBlank(message = "The title field is required") String title,
        @NotBlank(message = "The description field is required") String description,
        @NotNull(message = "The userId field is required") Long userId,
        @NotNull(message = "The categoryId field is required") Long categoryId
) {
}
