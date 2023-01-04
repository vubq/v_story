package com.v_story.v_story_be.Repositories;

import com.v_story.v_story_be.Entities.RefreshToken;
import com.v_story.v_story_be.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    @Modifying
    int deleteByUser(User user);
}
