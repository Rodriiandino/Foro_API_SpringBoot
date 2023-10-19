package com.one.foroapi.domain.dto.user;

public record UpdateUserDTO(
        String firstName,
        String lastName,
        String username,
        String email,
        String password
) {
}
