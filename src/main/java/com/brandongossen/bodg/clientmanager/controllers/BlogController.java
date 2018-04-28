package com.brandongossen.bodg.clientmanager.controllers;

import com.brandongossen.bodg.clientmanager.models.Blog;
import com.brandongossen.bodg.clientmanager.models.User;
import com.brandongossen.bodg.clientmanager.repositories.UsersRepository;
import com.brandongossen.bodg.clientmanager.services.BlogSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class BlogController {
    private final BlogSvc blogSvc;

    @Autowired
    public BlogController(BlogSvc blogSvc) {
        this.blogSvc = blogSvc;
    }

    @GetMapping("/blog")
    public String viewAllBlogTopics(Model viewModel) {

        Iterable<Blog> blog = blogSvc.findAllTopics();

        viewModel.addAttribute("blog", blog);

        return "blog/home";
    }
    @GetMapping("/blog/create")
    public String createBlogForm(Model viewModel) {
        viewModel.addAttribute("blog", new Blog());
        return "blog/create";
    }


    @PostMapping("/blog/create")
    public String createBlog(@Valid Blog blog, Errors validation, Model viewModel) {
        blog.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("blog", blog);
            return "/blog/create";
        }
        blogSvc.saveTopic(blog);
        return "redirect:/blog";
    }
}
