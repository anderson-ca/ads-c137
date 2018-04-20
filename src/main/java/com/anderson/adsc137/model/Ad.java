package com.anderson.adsc137.model;

import javax.persistence.*;

@Entity
@Table(name = "ads")
public class Ad {

    ///////////////////////////////////////////////////////////////////
    // Fields.
    ///////////////////////////////////////////////////////////////////
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;


    @Column(nullable = false, columnDefinition = "TEXT")
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    ///////////////////////////////////////////////////////////////////
    // Constructor method.
    ///////////////////////////////////////////////////////////////////
    public Ad() {

    }

    ///////////////////////////////////////////////////////////////////
    // Setters and Getters
    ///////////////////////////////////////////////////////////////////
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
