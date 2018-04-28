package com.brandongossen.bodg.clientmanager.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "The Blog must have a title!")
    @Size(min = 3, message = "The Blog must be at least 3 character long!")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "The Blog must have a description!")
    @Size(min = 10, message = "The description must be at least 10 character long!")
    private String body;

    @Column
    private boolean responsesAllowed;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;

    public Blog() {
    }
    

    public Blog(long id, String title, String body, boolean responsesAllowed, User user) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.responsesAllowed = responsesAllowed;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    public boolean getResponseAllowed() {
        return responsesAllowed;
    }
    
    public void setResponsesAllowed(boolean responsesAllowed) {
        this.responsesAllowed = responsesAllowed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


