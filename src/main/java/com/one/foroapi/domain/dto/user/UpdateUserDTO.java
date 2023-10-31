package com.one.foroapi.domain.dto.user;

import jakarta.validation.constraints.Email;

public record UpdateUserDTO(
        String firstName,
        String lastName,
        String username,
        @Email(message = "Invalid email address") String email,
        String password
) implements UserDTO {
}
