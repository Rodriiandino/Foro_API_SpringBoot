package com.one.foroapi.domain.service;

import com.one.foroapi.domain.dto.user.CreateUserDTO;
import com.one.foroapi.domain.dto.user.UpdateUserDTO;
import com.one.foroapi.domain.dto.user.UpdateUserForAdminDTO;
import com.one.foroapi.domain.model.User;
import com.one.foroapi.domain.repository.UserRepository;
import com.one.foroapi.util.Role;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserDTO createUserDTO) {
        User newUser = new User(createUserDTO);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    public Page<User> getAllUsers(Pageable pagination) {
        return userRepository.findAll(pagination);
    }

    public User getUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found: " + userId);
        }
        return userRepository.findById(userId).orElse(null);
    }

    public void deleteLogicalUserById(Long userId) {
        User user = getUserById(userId);
        if (user != null)
            user.deleteLogical();
        else {
            throw new EntityNotFoundException("User not found: " + userId);
        }
    }

    public User updateUserById(Long userId, UpdateUserDTO updateUserDTO) {
        User user = getUserById(userId);

        if (user != null) {

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
        } else {
            throw new EntityNotFoundException("User not found: " + userId);
        }
    }

    public User updateUserForAdminById(Long userId, UpdateUserForAdminDTO updateUserForAdminDTO) {
        User user = getUserById(userId);
        if (user != null) {
            if (updateUserForAdminDTO.role() != null) {
                user.setRole(updateUserForAdminDTO.role());
            }

            if (updateUserForAdminDTO.enabled() != null) {
                user.setEnabled(updateUserForAdminDTO.enabled());
            }
            return userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User not found: " + userId);
        }
    }
}
