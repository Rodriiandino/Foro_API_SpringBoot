package com.one.foroapi.domain.service.validations.user;

import com.one.foroapi.domain.dto.user.UpdateUserDTO;
import com.one.foroapi.domain.repository.UserRepository;
import com.one.foroapi.domain.service.validations.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserValidator extends UserValidations implements Validator<UpdateUserDTO> {

    private final UserRepository userRepository;

    @Autowired
    public UpdateUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void validate(UpdateUserDTO userDTO) {
        validations(userDTO, userRepository);
    }

}
