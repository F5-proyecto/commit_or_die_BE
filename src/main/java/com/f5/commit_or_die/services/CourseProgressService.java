package com.f5.commit_or_die.services;

import org.springframework.stereotype.Service;

import com.f5.commit_or_die.model.CourseProgress;
import com.f5.commit_or_die.repository.CourseProgressRepository;

@Service
public class CourseProgressService {

    private CourseProgressRepository courseProgressRepository;

    public CourseProgressService(CourseProgressRepository courseProgressRepository) {
        this.courseProgressRepository = courseProgressRepository;
    }

    public CourseProgress createProgress(CourseProgress courseProgress) {
        return courseProgressRepository.save(courseProgress);
    }

    public CourseProgress updateProgress(CourseProgress courseProgress) {
        return courseProgressRepository.save(courseProgress);
    }

    public CourseProgress getProgressByUserIdAndCourseId(Long userId, String courseId) {

        return courseProgressRepository.findByUser_IdAndCourseId(userId, courseId);
    }
}
