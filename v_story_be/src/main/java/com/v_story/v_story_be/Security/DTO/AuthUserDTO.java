package com.v_story.v_story_be.Security.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class AuthUserDTO {
    @Getter
    private final String username;
    private final String password;

    public AuthUserDTO(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.username = username.trim();
        this.password = password.trim();
    }

    public Optional<String> getPassword() {
        return Optional.ofNullable(password).map(p -> new BCryptPasswordEncoder().encode(p));
    }

    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
