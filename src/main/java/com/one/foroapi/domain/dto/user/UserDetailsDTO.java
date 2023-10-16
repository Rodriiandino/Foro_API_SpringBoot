package com.one.foroapi.domain.dto.user;

import com.one.foroapi.domain.model.User;
import com.one.foroapi.util.Role;

public record UserDetailsDTO(
        Long id,
        String firstName,
        String username,
        String lastName,
        String email,
        Role role,
        Boolean enabled
) {
    public UserDetailsDTO(User user) {
        this(
                user.getId(),
                user.getFirst_name(),
                user.getUsername(),
                user.getLast_name(),
                user.getEmail(),
                user.getRole(),
                user.getEnabled());
    }
}
