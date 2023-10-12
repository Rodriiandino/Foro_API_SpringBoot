package com.one.foroapi.domain.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePostDTO(
        @NotBlank String title,
        @NotBlank String content,
        @NotNull Long userId,
        @NotNull Long topicId
) {
}
