package com.v_story.v_story_be.Security.Jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.v_story.v_story_be.Security.DTO.AuthUserDTO;
import com.v_story.v_story_be.Security.Service.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {
    private final TokenAuthService tokenAuthService;
    private final UserDetailsServiceImpl userDetailsService;

    public StatelessLoginFilter(String urlMapping, TokenAuthService tokenAuthService,
                                UserDetailsServiceImpl userDetailsService, AuthenticationManager authenticationManager) {
        super(urlMapping);
        this.tokenAuthService = tokenAuthService;
        this.userDetailsService = userDetailsService;
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!request.getMethod().equals("POST")) {
            return null;
        }
        final AuthUserDTO user = this.toUser(request);
        final UsernamePasswordAuthenticationToken loginToken = user.toAuthenticationToken();
        return getAuthenticationManager().authenticate(loginToken);
    }

    private AuthUserDTO toUser(HttpServletRequest request) throws IOException {
        return new ObjectMapper().readValue(request.getInputStream(), AuthUserDTO.class);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        final UserDetails authenticatedUser = this.userDetailsService.loadUserByUsername(authResult.getName());
        final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);
        this.tokenAuthService.addJwtTokenToHeader(response, userAuthentication);
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
    }
}
