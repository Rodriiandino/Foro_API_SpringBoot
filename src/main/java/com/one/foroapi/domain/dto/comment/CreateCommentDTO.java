package com.one.foroapi.domain.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCommentDTO(
        @NotBlank(message = "The content field is required") String content,
        @NotNull(message = "The userId field is required") Long userId,
        @NotNull(message = "The postId field is required") Long postId
) {
}
