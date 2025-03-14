package com.f5.commit_or_die.model;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date publicationDate;
    
    private Integer rating;

    @NotEmpty(message = "The course identifier is required")
    private String resourceId;
     
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment() {}

    public Comment(Long id, String content, Date publicationDate, Integer rating, String resourceId, User user) {
        this.id = id;
        this.content = content;
        this.publicationDate = publicationDate;
        this.rating = rating;
        this.resourceId = resourceId;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
