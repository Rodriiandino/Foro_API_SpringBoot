package com.one.foroapi.domain.service;

import com.one.foroapi.domain.dto.user.CreateUserDTO;
import com.one.foroapi.domain.model.User;
import com.one.foroapi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserDTO createUserDTO) {
        User newUser = new User(createUserDTO);
        return userRepository.save(newUser);
    }


}
