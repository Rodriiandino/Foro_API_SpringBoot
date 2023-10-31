package com.one.foroapi.domain.service.validations;

import com.one.foroapi.domain.dto.user.CreateUserDTO;
import com.one.foroapi.domain.dto.user.UpdateUserDTO;

public interface Validator<T> {
    public void validate(T DTO);
}
