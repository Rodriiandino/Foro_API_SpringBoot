package com.one.foroapi.domain.model;

import com.one.foroapi.domain.dto.user.CreateUserDTO;
import com.one.foroapi.util.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity(name = "User")
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String username;
    private String last_name;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean enabled;

    public User(CreateUserDTO createUserDTO) {
        this.first_name = createUserDTO.firstName();
        this.last_name = createUserDTO.lastName();
        this.username = createUserDTO.username();
        this.email = createUserDTO.email();
        this.password = createUserDTO.password();
        this.role = Role.USER;
        this.enabled = true;
    }
}
