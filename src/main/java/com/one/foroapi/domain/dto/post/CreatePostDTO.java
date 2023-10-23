package com.one.foroapi.domain.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePostDTO(
        @NotBlank(message = "The title field is required") String title,
        @NotBlank(message = "The content field is required") String content,
        @NotNull(message = "The userId field is required") Long userId,
        @NotNull(message = "The topicId field is required") Long topicId
) {
}
