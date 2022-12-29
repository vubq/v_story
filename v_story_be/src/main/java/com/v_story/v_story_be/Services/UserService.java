package com.v_story.v_story_be.Services;

import com.v_story.v_story_be.Entities.User;
import com.v_story.v_story_be.Interfaces.RepositoryInterface;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends RepositoryInterface<User> {
}
