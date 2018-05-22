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

    @GetMapping("/response/{blog_id}/create")
    public String createResponseForm(@PathVariable long blog_id, Model viewModel) {
        Blog blog = blogSvc.findOneTopic(blog_id);
        if(!responsesAllowed(blog)){


//            validation.rejectValue(
//                    "comment",
//                    "response.comment",
//                    "Comments are not allowed for this blog!"
//            );
//        }
//
//        if (validation.hasErrors()) {
//            viewModel.addAttribute("errors", validation);
//            viewModel.addAttribute("blog", blog);
            return "redirect:/blog";
        }

        viewModel.addAttribute("response", new Response());
        viewModel.addAttribute("blog", blog);
        return "blog/create-comment";
    }


    @PostMapping("/response/{blog_id}/create")
    public String createNewResponse(@PathVariable long blog_id, @Valid @ModelAttribute("response") Response response, Errors validation, Model viewModel) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        response.setUser(user);
        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("response", response);
            return "/blog/create-comment";
        }
        Blog blog = blogSvc.findOneTopic(blog_id);
        response.setBlog(blog);
        responseDao.save(response);
        return "redirect:/blog";
    }

    @GetMapping("/response/{blog_id}/edit/{id}")
    public String viewEditResponseForm(@PathVariable long blog_id, @PathVariable long id, Model viewModel) {
        Blog blog = blogSvc.findOneTopic(blog_id);
        Response response = responseDao.findOne(id);
        viewModel.addAttribute("blog", blog);
        viewModel.addAttribute("response", response);
        return "/blog/edit-comment";
    }

    @PostMapping("/response/{blog_id}/edit/{id}")
    public String editResponse(@PathVariable long blog_id, @PathVariable long id, @Valid @ModelAttribute("response") Response editResponse, Errors validation, Model viewModel) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        editResponse.setUser(user);
        if (responseDao.findOne(id).getUser().getId() != (user.getId())) {
            return "redirect:/blog";
        }
        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("response", editResponse);
            return "/response/edit-comment";
        }

        Blog blog = blogSvc.findOneTopic(blog_id);
        editResponse.setBlog(blog);
        responseDao.save(editResponse);
        return "redirect:/blog";
    }

    @PostMapping("response/delete/{id}")
    public String deleteResponse(@PathVariable long id) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (responseDao.findOne(id).getUser().getId() != (user.getId())) {
            return "redirect:/blog";
        }

       Response response = responseDao.findOne(id);
        responseDao.delete(response);
        return "redirect:/blog";
    }

    private boolean responsesAllowed(Blog blog) {
        return blog.isResponsesAllowed();
    }

}
