package com.training.demo.common.security;

import com.training.demo.repository.UserRepository;
import com.training.demo.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserDetails user = userRepository
                            .findByEmail(email)
                        .orElseThrow(()-> new UsernameNotFoundException("email " + email + " not found"));

        return user;
    }
}
