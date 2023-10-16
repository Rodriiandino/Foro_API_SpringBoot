package com.one.foroapi.controller;

import com.one.foroapi.domain.dto.user.CreateUserDTO;
import com.one.foroapi.domain.dto.user.UserDetailsDTO;
import com.one.foroapi.domain.model.User;
import com.one.foroapi.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<UserDetailsDTO> createUser(@RequestBody @Valid CreateUserDTO createUserDTO) {
        User newUser = userService.createUser(createUserDTO);
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetailsDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UserDetailsDTO>> getAllUsers(@PageableDefault(size = 5) Pageable pagination) {
        Page<UserDetailsDTO> page = userService.getAllUsers(pagination).map(UserDetailsDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailsDTO> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(user);
        return ResponseEntity.ok(userDetailsDTO);
    }

}
