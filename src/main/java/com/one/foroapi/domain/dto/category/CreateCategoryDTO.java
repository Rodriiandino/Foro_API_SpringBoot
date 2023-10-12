package com.one.foroapi.domain.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryDTO(
        @NotBlank String name,
        @NotBlank String description
) {
}
