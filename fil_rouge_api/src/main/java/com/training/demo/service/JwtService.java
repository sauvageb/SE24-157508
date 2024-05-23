package com.training.demo.service;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String generateToken(String email) {
        return "MY-TOKEN";
    }


    public boolean isTokenValid(String headerToken) {
        return headerToken.equals("MY-TOKEN");
    }


    public String extractUserName(String headerToken) {
        return "sauvageboris.pro@gmail.com";
    }
}
