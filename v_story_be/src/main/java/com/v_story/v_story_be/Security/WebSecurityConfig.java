package com.v_story.v_story_be.Security;

import com.v_story.v_story_be.Security.Jwt.AuthEntryPointJwt;
import com.v_story.v_story_be.Security.Jwt.AuthTokenFilter;
import com.v_story.v_story_be.Security.Jwt.StatelessLoginFilter;
import com.v_story.v_story_be.Security.Jwt.TokenAuthService;
import com.v_story.v_story_be.Security.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    // securedEnabled = true,
    // jsr250Enabled = true,
    prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private TokenAuthService tokenAuthService;

    @Autowired
    private AuthEntryPointJwt authEntryPointJwt;

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .userDetailsService(userDetailsService)
//            .passwordEncoder(new BCryptPasswordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
            .disable();
        http.authorizeRequests()
            .antMatchers("/api/auth/**")
            .permitAll()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(authEntryPointJwt)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
            .antMatchers("/api/user/**")
            .hasAnyAuthority("SUPER_ADMIN");
        http
            .addFilterBefore(new StatelessLoginFilter("/api/auth/login", tokenAuthService, userDetailsService, authenticationManager()), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
//        http.cors().configurationSource(request -> {
//            final CorsConfiguration cors = new CorsConfiguration();
//            cors.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:8000"));
//            cors.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//            cors.setAllowCredentials(true);
//            cors.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", TokenAuthService.AUTH_HEADER_NAME,TokenAuthService.AUTH_USERNAME, "x-file-name"));
//            cors.setExposedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", TokenAuthService.AUTH_HEADER_NAME,TokenAuthService.AUTH_USERNAME, "x-file-name"));
//            return cors;
//        });
    }
}
