package com.training.demo.common.security;

import com.training.demo.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthJwtTokenFilter extends OncePerRequestFilter {

    private static final String BEARER_TOKEN_PREFIX = "Bearer ";
    private final UserService userService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String headerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (headerToken != null && headerToken.startsWith(BEARER_TOKEN_PREFIX)) {

            headerToken = StringUtils.delete(headerToken, BEARER_TOKEN_PREFIX).trim();
            if (jwtService.isTokenValid(headerToken)) {
                String username = jwtService.extractUserName(headerToken);

                UserDetails userDetails = userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);

    }
}
