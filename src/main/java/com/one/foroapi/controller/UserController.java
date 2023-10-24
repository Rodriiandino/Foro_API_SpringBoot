package com.one.foroapi.controller;

import com.one.foroapi.domain.dto.user.CreateUserDTO;
import com.one.foroapi.domain.dto.user.UpdateUserDTO;
import com.one.foroapi.domain.dto.user.UpdateUserForAdminDTO;
import com.one.foroapi.domain.dto.user.UserDetailsDTO;
import com.one.foroapi.domain.model.User;
import com.one.foroapi.domain.service.UserService;
import com.one.foroapi.infra.exceptions.CustomErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Users", description = "API for managing forum users. Users are those who post messages in the forum. They can be normal users or administrators. Administrators can manage users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @Transactional
    @Operation(
            summary = "Create a new user",
            description = "Create a new user"
    )
    @ApiResponse(
            responseCode = "201",
            description = "User created successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDetailsDTO.class)
            )
    )
    public ResponseEntity<UserDetailsDTO> createUser(@RequestBody @Valid CreateUserDTO createUserDTO) {
        User newUser = userService.createUser(createUserDTO);
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetailsDTO);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get All Users",
            description = "Retrieve a list of all forum users."
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of forum users.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDetailsDTO.class)
            )
    )
    public ResponseEntity<Page<UserDetailsDTO>> getAllUsers(@PageableDefault(size = 5) Pageable pagination) {
        Page<UserDetailsDTO> page = userService.getAllUsers(pagination).map(UserDetailsDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{userId}")
    @Operation(
            summary = "Get User by Id",
            description = "Retrieve a user by its id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "User found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDetailsDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "User not found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)
            )
    )
    public ResponseEntity<UserDetailsDTO> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(user);
        return ResponseEntity.ok(userDetailsDTO);
    }


    @PutMapping("/{userId}")
    @Transactional
    @Operation(
            summary = "Update a user by id",
            description = "Update a user by its id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User updated successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDetailsDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "User not found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)
            )
    )
    public ResponseEntity<UserDetailsDTO> updateUserById(@PathVariable Long userId, @RequestBody @Valid UpdateUserDTO updateUserDTO) {
        User user = userService.updateUserById(userId, updateUserDTO);
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(user);
        return ResponseEntity.ok(userDetailsDTO);
    }

    @PutMapping("/admin/{userId}")
    @Transactional
    @Operation(
            summary = "Update a user by id for admin",
            description = "Update a user by its id for admin"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User updated successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDetailsDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "User not found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)
            )
    )
    public ResponseEntity<UserDetailsDTO> updateUserForAdminById(@PathVariable Long userId, @RequestBody @Valid UpdateUserForAdminDTO updateUserForAdminDTO) {
        User user = userService.updateUserForAdminById(userId, updateUserForAdminDTO);
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(user);
        return ResponseEntity.ok(userDetailsDTO);
    }

    @DeleteMapping("/{userId}")
    @Transactional
    @Operation(
            summary = "Delete a user by id",
            description = "Delete a user by its id"
    )
    @ApiResponse(
            responseCode = "204",
            description = "User deleted successfully."
    )
    @ApiResponse(
            responseCode = "404",
            description = "User not found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)
            )
    )
    public ResponseEntity<Void> deleteLogicalUserById(@PathVariable Long userId) {
        userService.deleteLogicalUserById(userId);
        return ResponseEntity.noContent().build();
    }


}
