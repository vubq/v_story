package com.v_story.v_story_be.Config.Security.Service;

import com.v_story.v_story_be.Entities.Role;
import com.v_story.v_story_be.Entities.User;
import com.v_story.v_story_be.Services.Implements.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userService.getByUserName(username).get();
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> role = user.getRoles();
        for (Role r : role) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getClass().getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }
}
