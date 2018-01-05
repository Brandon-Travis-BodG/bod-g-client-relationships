package com.brandongossen.bodg.clientmanager.models;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "goals")

public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String mainGoal;

    @Column(nullable = true)
    private String secondaryGoal;
    //Look over this

    @ManyToMany(cascade = ALL)
    @JoinTable(
            name="users_goals",
            joinColumns={@JoinColumn(name="goal_id")},
            inverseJoinColumns={@JoinColumn(name="user_id")}
    )
    private List<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMainGoal() {
        return mainGoal;
    }

    public void setMainGoal(String mainGoal) {
        this.mainGoal = mainGoal;
    }

    public String getSecondaryGoal() {
        return secondaryGoal;
    }

    public void setSecondaryGoal(String secondaryGoal) {
        this.secondaryGoal = secondaryGoal;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

