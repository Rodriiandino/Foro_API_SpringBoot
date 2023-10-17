package com.one.foroapi.domain.dto.user;

import com.one.foroapi.domain.model.User;
import com.one.foroapi.util.Role;

import java.time.LocalDateTime;

public record UserDetailsDTO(
        Long id,
        String firstName,
        String username,
        String lastName,
        String email,
        Role role,
        Boolean enabled,
        LocalDateTime createdAt
) {
    public UserDetailsDTO(User user) {
        this(
                user.getId(),
                user.getFirst_name(),
                user.getUsername(),
                user.getLast_name(),
                user.getEmail(),
                user.getRole(),
                user.getEnabled(),
                user.getCreated_at());
    }
}
