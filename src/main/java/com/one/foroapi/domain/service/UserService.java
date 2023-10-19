package com.one.foroapi.domain.service;

import com.one.foroapi.domain.dto.user.CreateUserDTO;
import com.one.foroapi.domain.dto.user.UpdateUserDTO;
import com.one.foroapi.domain.dto.user.UpdateUserForAdminDTO;
import com.one.foroapi.domain.model.User;
import com.one.foroapi.domain.repository.UserRepository;
import com.one.foroapi.util.Role;
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

    public User updateUserById(Long userId, UpdateUserDTO updateUserDTO) {
        User user = getUserById(userId);

        if (updateUserDTO.firstName() != null) {
            user.setFirst_name(updateUserDTO.firstName());
        }

        if (updateUserDTO.lastName() != null) {
            user.setLast_name(updateUserDTO.lastName());
        }

        if (updateUserDTO.username() != null) {
            user.setUsername(updateUserDTO.username());
        }

        if (updateUserDTO.email() != null) {
            user.setEmail(updateUserDTO.email());
        }

        if (updateUserDTO.password() != null) {
            user.setPassword(updateUserDTO.password());
        }

        return userRepository.save(user);
    }

    public User updateUserForAdminById(Long userId, UpdateUserForAdminDTO updateUserForAdminDTO) {
        User user = getUserById(userId);
        if (updateUserForAdminDTO.role() != null) {
            user.setRole(updateUserForAdminDTO.role());
        }

        if (updateUserForAdminDTO.enabled() != null) {
            user.setEnabled(updateUserForAdminDTO.enabled());
        }
        return userRepository.save(user);
    }
}
