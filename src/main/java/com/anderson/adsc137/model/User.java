package com.anderson.adsc137.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    ///////////////////////////////////////////////////////////////////
    // Fields.
    ///////////////////////////////////////////////////////////////////
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Ad> ads;

    ///////////////////////////////////////////////////////////////////
    // JSON usage.
    ///////////////////////////////////////////////////////////////////
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Ad> JSONads;

    ///////////////////////////////////////////////////////////////////
    // Constructor method.
    ///////////////////////////////////////////////////////////////////
    public User() {

    }

    ///////////////////////////////////////////////////////////////////
    // Constructor method. (Authentication -> login/logout)
    ///////////////////////////////////////////////////////////////////
    public User(User copy) {
        id = copy.id;
        username = copy.username;
        email = copy.email;
        password = copy.password;
    }

    ///////////////////////////////////////////////////////////////////
    // Setters and Getters
    ///////////////////////////////////////////////////////////////////
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() { return this.id; }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public List<Ad> getAds() {
        return this.ads;
    }

}
