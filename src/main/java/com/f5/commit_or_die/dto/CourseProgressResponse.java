package com.f5.commit_or_die.dto;

import jakarta.validation.constraints.NotNull;

public class CourseProgressResponse {
    private Long id;

    @NotNull(message = "El ID del curso es obligatorio")
    private String courseId;

    private double progressPercentage;
    private int completedLessonsCount;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long userId;

    public CourseProgressResponse() {
    }

    public CourseProgressResponse(Long id, String courseId, double progressPercentage, int completedLessonsCount,
            Long userId) {
        this.id = id;
        this.courseId = courseId;
        this.progressPercentage = progressPercentage;
        this.completedLessonsCount = completedLessonsCount;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public double getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(double progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public int getCompletedLessonsCount() {
        return completedLessonsCount;
    }

    public void setCompletedLessonsCount(int completedLessonsCount) {
        this.completedLessonsCount = completedLessonsCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
