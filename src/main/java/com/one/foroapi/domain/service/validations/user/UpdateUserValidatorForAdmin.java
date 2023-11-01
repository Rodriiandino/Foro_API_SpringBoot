package com.one.foroapi.domain.service.validations.user;

import com.one.foroapi.domain.dto.user.UpdateUserForAdminDTO;
import com.one.foroapi.domain.repository.UserRepository;
import com.one.foroapi.domain.service.validations.Validator;
import com.one.foroapi.infra.exceptions.Validations;
import com.one.foroapi.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserValidatorForAdmin implements Validator<UpdateUserForAdminDTO> {
    @Override
    public void validate(UpdateUserForAdminDTO DTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().stream().noneMatch(authority ->
                authority.getAuthority().equals(Role.ADMIN.toString()))) {
            throw new Validations("You don't have permission to update this user");
        }
    }
}
