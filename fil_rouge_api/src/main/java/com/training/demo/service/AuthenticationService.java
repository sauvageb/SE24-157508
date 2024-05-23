package com.training.demo.service;

import com.training.demo.common.dto.JwtAuthenticationResponse;
import com.training.demo.common.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public JwtAuthenticationResponse login(LoginRequest dto) {

        UsernamePasswordAuthenticationToken usernamePasswordToken = new UsernamePasswordAuthenticationToken(
                dto.getEmail(),
                dto.getPassword());

        // User Authentification
        Authentication authentication = authenticationManager.authenticate(usernamePasswordToken);

        // JWT Token generation
        String jwt = jwtService.generateToken(dto.getEmail());

        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


}
