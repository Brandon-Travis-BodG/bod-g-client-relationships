package com.brandongossen.bodg.clientmanager.controllers;

import com.brandongossen.bodg.clientmanager.models.Blog;
import com.brandongossen.bodg.clientmanager.models.Response;
import com.brandongossen.bodg.clientmanager.models.User;
import com.brandongossen.bodg.clientmanager.repositories.ResponseRepository;
import com.brandongossen.bodg.clientmanager.repositories.UsersRepository;
import com.brandongossen.bodg.clientmanager.services.BlogSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class BlogController {
    private final BlogSvc blogSvc;
    private final ResponseRepository responseDao;

    @Autowired
    public BlogController(BlogSvc blogSvc, ResponseRepository responseDao) {
        this.blogSvc = blogSvc;
        this.responseDao = responseDao;
    }

    @GetMapping("/blog")
    public String viewAllBlogTopics(Model viewModel) {

        Iterable<Blog> blog = blogSvc.findAllTopics();

        viewModel.addAttribute("blog", blog);

        return "blog/home";
    }

    @GetMapping("/blog/{id}")
    public String viewIndividualPost(@PathVariable long id, Model viewModel) {

        Blog blog = blogSvc.findOneTopic(id);

        viewModel.addAttribute("blog", blog);
        return "blog/show";
    }

    @GetMapping("/blog/create")
    public String createBlogForm(Model viewModel) {
        viewModel.addAttribute("blog", new Blog());
        return "blog/create";
    }


    @PostMapping("/blog/create")
    public String createBlog(@Valid @ModelAttribute("blog") Blog createBlog, Errors validation, Model viewModel) {
        createBlog.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("blog", createBlog);
            return "/blog/create";
        }
        blogSvc.saveTopic(createBlog);
        return "redirect:/blog";
    }

    @GetMapping("/blogs/{id}/edit")
    public String viewEditBlogForm(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("blog", blogSvc.findOneTopic(id));
        return "/blog/edit";
    }

    @PostMapping("/blogs/{id}/edit")
    public String editBlog(@PathVariable long id, @Valid @ModelAttribute("blog") Blog editBlog, Errors validation, Model viewModel) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (blogSvc.findOneTopic(id).getUser().getId() != (user.getId())) {
            return "redirect:/blogs";
        }


        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("blog", editBlog);
            return "blog/edit";
        }


        editBlog.setUser(user);
        blogSvc.saveTopic(editBlog);
        return "redirect:/blog";
    }

    @PostMapping("blogs/{id}/delete")
    public String deleteBlog(@PathVariable long id) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (blogSvc.findOneTopic(id).getUser().getId() != (user.getId())) {
            return "redirect:/blog";
        }

        Blog blog = blogSvc.findOneTopic(id);
        blogSvc.deleteTopic(blog);
        return "redirect:/blog";
    }

    @GetMapping("/response/create")
    public String createResponseForm(Model viewModel) {
        viewModel.addAttribute("response", new Response());
        return "blog/create-comment";
    }


    @PostMapping("/response/create")
    public String createNewResponse(@Valid @ModelAttribute("response") Response response, Errors validation, Model viewModel, Blog blog, User user) {
        response.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("response", response);
            return "/blog/create-comment";
        }
        response.setUser(user);
        response.setBlog(blog);
        responseDao.save(response);
        return "redirect:/blog";
    }

//    @GetMapping("/response/create")
//    public String createResponseForm(@PathVariable long id, Model viewModel, User user) {
//
//        Blog blog = blogSvc.findOneTopic(id);
//
//
//        if (blog != null) {
//            Response response = new Response();
//            response.setBlog(blog);
//            response.setUser(user);
//
//            viewModel.addAttribute("user", user);
//
//            return "/blog/create/comment";
//
//        } else {
//            return "/error";
//        }
//
//
//    }
//
//
//    @PostMapping("/response/create")
//    public String createNewResponse(@PathVariable long id, @Valid @ModelAttribute("response") Response response, Errors validation,  Model viewModel) {
//        if (validation.hasErrors()) {
//            viewModel.addAttribute("errors", validation);
//            viewModel.addAttribute("response", response);
//            return "/response/create";
//        }
//        responseDao.save(response);
//        return "redirect:/blog/" + response.getBlog().getId();
//
//
//    }
}
