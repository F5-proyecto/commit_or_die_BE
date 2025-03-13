package com.f5.commit_or_die.dto;

public class CommentResponse {

    private Long id;
    private String content;
    private String resourceId;
    private Integer rating;
    private Long userId;

    public CommentResponse() {
    }

    public CommentResponse(Long id, String content, String resourceId, Integer rating, Long userId) {
        this.id = id;
        this.content = content;
        this.resourceId = resourceId;
        this.rating = rating;
        this.userId = userId;
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

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
