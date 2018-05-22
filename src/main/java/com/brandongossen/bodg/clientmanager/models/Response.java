package com.brandongossen.bodg.clientmanager.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "A response can not be empty!")
    private String comment;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "blog_id")
    private Blog blog;



    public Response(){}

    public Response(String comment, User user, Blog blog) {
        this.comment = comment;
        this.user = user;
        this.blog = blog;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

}
