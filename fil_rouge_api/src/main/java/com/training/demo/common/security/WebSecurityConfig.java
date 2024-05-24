package com.training.demo.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AuthJwtTokenFilter authenticationJwtTokenFilter;


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Filter security on URLS
    // URLS secured or not
    // URLS authorization (role...ADMIN..USER)
    // CSRF Configuration (Cross-Site Request Forgery)
    // CORS Configuration (Cross-Origin Resource Sharing)
    // Session Management (Stateless..etc)
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable CORS
        http.cors(CorsConfigurer::disable);
        // Disable CSRF
        http.csrf(CsrfConfigurer::disable);
        // Session Policy Management
        http.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(requests-> {
            requests
                    .requestMatchers("/public/**").permitAll()
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers(antMatcher(HttpMethod.POST, "/api/tutorials/**")).hasAuthority("ROLE_ADMIN")
                    // all other http queries need to be authenticated
                    .anyRequest().authenticated();
        });

        // Used before the classic securityFilterChain
        // Token validation
        http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
