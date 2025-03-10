package com.f5.commit_or_die.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course_progress")
public class CourseProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseId;
    private double progressPercentage;
    private int completedLessonsCount;
    private String user;

    public CourseProgress() {
    }

    public CourseProgress(Long id, String courseId, double progressPercentage, int completedLessonsCount, String user) {
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
