package com.f5.commit_or_die.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "course_progress")
public class CourseProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Course ID is required")
    private String courseId;

    private double progressPercentage;
    private int completedLessonsCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    public CourseProgress() {
    }

    public CourseProgress(Long id, String courseId, double progressPercentage, int completedLessonsCount, User user) {
        this.id = id;
        this.courseId = courseId;
        this.progressPercentage = progressPercentage;
        this.completedLessonsCount = completedLessonsCount;
        this.user = user;
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

    public void setProgressPercentage(double progressPercentageId) {
        this.progressPercentage = progressPercentageId;
    }

    public int getCompletedLessonsCount() {
        return completedLessonsCount;
    }

    public void setCompletedLessonsCount(int completedLessonsCount) {
        this.completedLessonsCount = completedLessonsCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
