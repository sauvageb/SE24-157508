package com.training.demo.api;

import com.training.demo.common.dto.JwtAuthenticationResponse;
import com.training.demo.common.dto.LoginRequest;
import com.training.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest dto) {

        JwtAuthenticationResponse token = authenticationService.login(dto);

        return ResponseEntity.ok(token);
    }

}
