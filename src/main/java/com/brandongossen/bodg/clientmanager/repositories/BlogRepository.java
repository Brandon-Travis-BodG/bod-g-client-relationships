package com.brandongossen.bodg.clientmanager.repositories;

import com.brandongossen.bodg.clientmanager.models.Blog;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog, Long> {
}
