package com.brandongossen.bodg.clientmanager.repositories;

import com.brandongossen.bodg.clientmanager.models.Blog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends CrudRepository<Blog, Long> {
    @Query(value = "SELECT * FROM blog b WHERE " + "LOWER(b.title) LIKE LOWER(CONCAT('%',:searchWord, '%')) OR " + "LOWER(b.body) LIKE LOWER(CONCAT('%',:searchWord, '%'))",
            nativeQuery = true)
    List<Blog> searchBar(@Param("searchWord") String searchWord);
}