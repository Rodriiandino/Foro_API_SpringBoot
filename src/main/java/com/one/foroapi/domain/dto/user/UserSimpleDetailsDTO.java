package com.one.foroapi.domain.dto.user;

import com.one.foroapi.domain.model.User;
import com.one.foroapi.util.Role;

public record UserSimpleDetailsDTO(
        Long id,
        String username,
        Role role
) {
    public UserSimpleDetailsDTO(User user) {
        this(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }
}
