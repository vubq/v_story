package com.v_story.v_story_be.Config.Security.Service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserAuthentication implements Authentication {
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -1139920528880230251L;

    /**
     * The user.
     */
    private final UserDetails userDetails;

    /**
     * The authenticated.
     */
    private boolean authenticated = true;

    public UserAuthentication(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public String getName() {
        return userDetails.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return userDetails.getPassword();
    }

    @Override
    public UserDetails getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return userDetails.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }
}
