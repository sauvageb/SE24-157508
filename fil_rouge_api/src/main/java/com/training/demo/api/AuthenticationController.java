package com.training.demo.api;

import com.training.demo.common.dto.JwtAuthenticationResponse;
import com.training.demo.common.dto.LoginRequest;
import com.training.demo.common.dto.RegisterRequest;
import com.training.demo.common.dto.UserDto;
import com.training.demo.common.security.UserService;
import com.training.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest dto){

        authenticationService.register(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest dto) {

        JwtAuthenticationResponse token = authenticationService.login(dto);

        logger.info("login()");

        return ResponseEntity.ok(token);
    }

}
