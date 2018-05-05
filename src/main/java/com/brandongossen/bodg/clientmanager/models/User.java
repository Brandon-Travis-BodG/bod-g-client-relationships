package com.brandongossen.bodg.clientmanager.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "First name can not be blank!")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Last name can not be blank!")
    private String lastName;

    @Column(nullable = false)
    @NotBlank(message = "Email can not be blank!")
    @Email(message = "Email must have an @ symbol!")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Phone number can not be blank!")
    @Size(min=10, message = "Phone number must be 10 digits String!")
    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}",
            message = "Invalid phone number")
    private String phoneNumber;

    @Column(nullable = false)
    @NotBlank(message = "Username can not be blank!")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Password can not be blank!")
    private String password;

    @Column(nullable = false)
    @NumberFormat(style= NumberFormat.Style.NUMBER)
//    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Age should be a number")
//    @NotBlank(message = "Age can not be blank!")
//    @Digits(integer = 2, fraction = 0, message = "Age must be a number")
    private short age;

    @Column(nullable = false)
    private Gender gender;
    //enum gives things to choose from
    //char allow a single character

    @Column(nullable = false)
    @NumberFormat(style= NumberFormat.Style.NUMBER)
//    @NotBlank(message = "Height can not be blank!")
    private short height;

    @Column(nullable = false)
    @NumberFormat(style= NumberFormat.Style.NUMBER)
//    @NotBlank(message = "Weight can not be blank!")
    private double weight;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonBackReference
    private List<HealthInfo> healthInfos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonBackReference
    private List<Blog> blogs;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonBackReference
    private List<Response> responses;

    @ManyToMany(cascade = ALL, mappedBy = "users")
    private List<Goal> goals;

    public User() {
    }

    public User(User copy) {
        id = copy.id;
        firstName = copy.firstName;
        lastName = copy.lastName;
        email = copy.email;
        phoneNumber = copy.phoneNumber;
        username = copy.username;
        password = copy.password;
        age = copy.age;
        gender = copy.gender;
        height = copy.height;
        weight = copy.weight;
    }

    public User(long id, String firstName, String lastName, String email, String phoneNumber, String username, String password, short age, Gender gender, short height, double weight) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    public User(String firstName, String lastName, String email, String phoneNumber, String username, String password, short age, Gender gender, short height, double weight, List<HealthInfo> healthInfos, List<Blog> blogs, List<Response> responses, List<Goal> goals) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.healthInfos = healthInfos;
        this.blogs = blogs;
        this.responses = responses;
        this.goals = goals;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public short getHeight() {
        return height;
    }

    public void setHeight(short height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<HealthInfo> getHealthInfos() {
        return healthInfos;
    }

    public void setHealthInfos(List<HealthInfo> healthInfos) {
        this.healthInfos = healthInfos;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }
}