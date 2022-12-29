package com.v_story.v_story_be.Config.Security;

import com.v_story.v_story_be.Config.Security.Filter.JwtRequestFilter;
import com.v_story.v_story_be.Config.Security.Filter.StatelessLoginFilter;
import com.v_story.v_story_be.Config.Security.Service.AppUserDetailsService;
import com.v_story.v_story_be.Config.Security.Service.JwtAuthenticationEntryPoint;
import com.v_story.v_story_be.Config.Security.Service.TokenAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Autowired
    private TokenAuthService tokenAuthService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/api/**").permitAll().and()


                /**
                 * make sure we use stateless session; session won't be used to store user's
                 * state.
                 */
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(new StatelessLoginFilter("/api/auth/login", tokenAuthService, appUserDetailsService,
                authenticationManager()), UsernamePasswordAuthenticationFilter.class);
        // Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors().configurationSource(request -> {
            final CorsConfiguration cors = new CorsConfiguration();
            cors.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:8000"));
            cors.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
            cors.setAllowCredentials(true);
            cors.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", TokenAuthService.AUTH_HEADER_NAME, TokenAuthService.AUTH_USERNAME, "x-file-name"));
            cors.setExposedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", TokenAuthService.AUTH_HEADER_NAME, TokenAuthService.AUTH_USERNAME, "x-file-name"));
            return cors;
        });

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());

    }
}
