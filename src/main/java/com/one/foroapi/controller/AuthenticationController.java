package com.one.foroapi.controller;

import com.one.foroapi.domain.dto.user.UserAuthenticationDTO;
import com.one.foroapi.domain.model.User;
import com.one.foroapi.infra.security.JWTtoken;
import com.one.foroapi.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
@Tag(name = "Authentication", description = "Gets the designated user's token for endpoint access")
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    @Transactional
    @Operation(
            summary = "Authenticate a user",
            description = "Authenticate a user with a username and password and return a JWT token."
    )
    @ApiResponse(
            responseCode = "200",
            description = "User authenticated successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = JWTtoken.class)
            )
    )
    public ResponseEntity<JWTtoken> authenticateUser(@RequestBody @Valid UserAuthenticationDTO userAuthentication) {

        Authentication authToken = new UsernamePasswordAuthenticationToken(userAuthentication.username(),
                userAuthentication.password());

        Authentication authentication = authenticationManager.authenticate(authToken);

        String token = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new JWTtoken(token));
    }

}
