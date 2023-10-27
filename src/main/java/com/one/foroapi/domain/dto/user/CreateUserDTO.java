package com.one.foroapi.domain.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public record CreateUserDTO(
        @NotBlank(message = "The firstName field is required") String firstName,
        @NotBlank(message = "The username field is required") String username,
        @NotBlank(message = "The lastName field is required") String lastName,
        @NotBlank(message = "The email field is required") @Email(message = "Invalid email address") String email,
        @NotBlank(message = "The password field is required") String password) {
}
