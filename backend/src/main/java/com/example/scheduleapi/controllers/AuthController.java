package com.example.scheduleapi.controllers;

import com.example.scheduleapi.dtos.AuthResponseDto;
import com.example.scheduleapi.dtos.LoginDto;
import com.example.scheduleapi.models.UserModel;
import com.example.scheduleapi.security.JwtAuthenticationResponse;
import com.example.scheduleapi.security.JwtTokenProvider;
import com.example.scheduleapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<?> authenticateUser (@RequestBody @Valid LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);

        Optional<UserModel> user = userService.findByEmail(loginDto.getEmail());

        if (user.isPresent()) {
            AuthResponseDto responseDto = new AuthResponseDto(jwt, user.get());
            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}