package com.one.foroapi.domain.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryDTO(
        @NotBlank(message = "The name field is required") String name,
        @NotBlank(message = "The description field is required") String description
) {
}
