package com.one.foroapi.domain.service.validations.user;

import com.one.foroapi.domain.dto.user.CreateUserDTO;
import com.one.foroapi.domain.repository.UserRepository;
import com.one.foroapi.domain.service.validations.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUserValidator extends UserValidations implements Validator<CreateUserDTO> {

    private final UserRepository userRepository;

    @Autowired
    public CreateUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(CreateUserDTO userDTO) {
        validations(userDTO, userRepository);
    }
}


