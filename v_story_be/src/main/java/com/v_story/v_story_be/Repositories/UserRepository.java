package com.v_story.v_story_be.Repositories;

import com.v_story.v_story_be.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName (String userName);
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);
}
