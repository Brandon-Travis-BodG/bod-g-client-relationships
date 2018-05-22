package com.brandongossen.bodg.clientmanager.services;

import com.brandongossen.bodg.clientmanager.models.Blog;
import com.brandongossen.bodg.clientmanager.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("blogSvc")
public class BlogSvc {
    private final BlogRepository blogDao;

    @Autowired
    public BlogSvc(BlogRepository blogDao){
        this.blogDao = blogDao;
    }

    public Iterable<Blog> findAllTopics(){
        return blogDao.findAll();
    }

    public Blog findOneTopic(long id){
        return blogDao.findOne(id);
    }

    public Blog saveTopic(Blog blog){
        return blogDao.save(blog);
    }

    public Blog deleteTopic(Blog blog){
        blogDao.delete(blog);
        return null;
    }
    public List<Blog> searchForBlog(String searchWord) {
        return blogDao.searchBar(searchWord);
    }
}
