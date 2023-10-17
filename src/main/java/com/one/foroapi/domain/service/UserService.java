package com.one.foroapi.domain.service;

import com.one.foroapi.domain.dto.user.CreateUserDTO;
import com.one.foroapi.domain.model.User;
import com.one.foroapi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Page<User> getAllUsers(Pageable pagination) {
        return userRepository.findAll(pagination);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void deleteLogicalUserById(Long userId) {
        User user = getUserById(userId);
        user.deleteLogical();
    }
}
