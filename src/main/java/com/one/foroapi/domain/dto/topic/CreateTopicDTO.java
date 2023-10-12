package com.one.foroapi.domain.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTopicDTO(
        @NotBlank String title,
        @NotBlank String content,
        @NotNull Long userId,
        @NotNull Long categoryId
) {
}
