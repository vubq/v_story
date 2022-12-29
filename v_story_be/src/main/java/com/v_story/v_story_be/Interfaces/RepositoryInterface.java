package com.v_story.v_story_be.Interfaces;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<T> {
    public List<T> getAll();
    public Optional<T> get(Integer id);
    public Optional<T> save(T t);
}
