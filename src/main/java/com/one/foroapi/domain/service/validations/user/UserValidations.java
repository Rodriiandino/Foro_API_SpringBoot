package com.one.foroapi.domain.service.validations.user;

import com.one.foroapi.domain.dto.user.UserDTO;
import com.one.foroapi.domain.repository.UserRepository;
import com.one.foroapi.infra.exceptions.Validations;

public class UserValidations {
    public static <T extends UserDTO> void validations(T userDTO, UserRepository userRepository) {
        if (userRepository.findByUsername(userDTO.username()) != null) {
            throw new Validations("Username already exists");
        }

        if (userRepository.findByEmail(userDTO.email()) != null) {
            throw new Validations("Email already exists");
        }

        if (userDTO.password() != null) {
            if (userDTO.password().length() < 8) {
                throw new Validations("Password must be at least 8 characters long");
            }
        }
    }
}
