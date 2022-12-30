package com.v_story.v_story_be.Config.Security.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class TokenAuthService {
    @Autowired
    private JwtTokenUtil jwtToken;
    @Autowired
    private AppUserDetailsService appUserDetailsService;

    public static final String AUTH_HEADER_NAME = "token";
    public static final String AUTH_USERNAME = "username";

    public void addJwtTokenToHeader(HttpServletResponse response, UserAuthentication authentication) {
        final UserDetails user = authentication.getDetails();
        response.addHeader(AUTH_HEADER_NAME, jwtToken.generateToken(user));
        response.addHeader(AUTH_USERNAME, user.getUsername());
    }

    public Authentication generateAuthenticationFromRequest(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token == null || token.isEmpty())
            return null;
        return new UserAuthentication(appUserDetailsService.loadUserByUsername(this.jwtToken.getUsernameFromToken(token)));
    }
}
