package com.training.demo.service;

import com.training.demo.common.dto.JwtAuthenticationResponse;
import com.training.demo.common.dto.LoginRequest;
import com.training.demo.common.dto.RegisterRequest;
import com.training.demo.common.exception.UserAlreadyExistException;
import com.training.demo.repository.RoleRepository;
import com.training.demo.repository.UserRepository;
import com.training.demo.repository.entity.Role;
import com.training.demo.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

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

    public void register(RegisterRequest dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(dto.getEmail());
        }

        // All roles affected to new registration
        List<Role> allRoles = (List<Role>) roleRepository.findAll();

        User newUser = User
                .builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .roleList(allRoles)
                .build();

        userRepository.save(newUser);
    }
}
