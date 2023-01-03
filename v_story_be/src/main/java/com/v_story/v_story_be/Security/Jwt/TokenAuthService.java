package com.v_story.v_story_be.Security.Jwt;

import com.v_story.v_story_be.Security.Service.UserDetailsImpl;
import com.v_story.v_story_be.Security.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class TokenAuthService {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public static final String AUTH_HEADER_NAME = "token";
    public static final String AUTH_USERNAME = "username";

    public void addJwtTokenToHeader(HttpServletResponse response, UserAuthentication authentication) {
        final UserDetails user = authentication.getDetails();
        response.addHeader(AUTH_HEADER_NAME, jwtUtils.generateJwtToken((UserDetailsImpl) user));
        response.addHeader(AUTH_USERNAME, user.getUsername());
    }
///
    public Authentication generateAuthenticationFromRequest(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token == null || token.isEmpty())
            return null;
        return new UserAuthentication(userDetailsService.loadUserByUsername(this.jwtUtils.getUserNameFromJwtToken(token)));
    }
}
