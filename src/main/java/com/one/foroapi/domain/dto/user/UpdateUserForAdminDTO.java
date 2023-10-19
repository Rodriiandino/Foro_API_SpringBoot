package com.one.foroapi.domain.dto.user;

import com.one.foroapi.util.Role;

public record UpdateUserForAdminDTO(
        Role role,
        Boolean enabled
) {
}
