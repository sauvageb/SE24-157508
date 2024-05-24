package com.training.demo.service;

import com.training.demo.common.dto.JwtAuthenticationResponse;
import com.training.demo.common.dto.LoginRequest;
import com.training.demo.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

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

        User userDetails = (User) authentication.getPrincipal();

        Map<String, Object> claims = Map.of("roles", authentication.getAuthorities());

        // JWT Token generation
        String jwt = jwtService.generateToken(claims, userDetails.getEmail());

        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


}
