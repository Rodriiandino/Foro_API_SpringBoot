package com.one.foroapi.domain.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserAuthenticationDTO(
        @NotBlank(message = "The username field is required") String username,
        @NotBlank(message = "The password field is required") String password
) {
}
