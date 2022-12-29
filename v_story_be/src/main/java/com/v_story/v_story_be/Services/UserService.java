package com.v_story.v_story_be.Services;

import com.v_story.v_story_be.Entities.User;
import com.v_story.v_story_be.Interfaces.RepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService extends RepositoryInterface<User> {
    Optional<User> getByUserName (String userName);
}
