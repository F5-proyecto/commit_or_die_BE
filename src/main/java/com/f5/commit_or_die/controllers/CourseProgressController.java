package com.f5.commit_or_die.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.f5.commit_or_die.model.CourseProgress;
import com.f5.commit_or_die.services.CourseProgressService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/course-progress")
public class CourseProgressController {

    private CourseProgressService courseProgressService;

    public CourseProgressController(CourseProgressService courseProgressService) {
        this.courseProgressService = courseProgressService;
    }

    @PutMapping("/update")
    public ResponseEntity<CourseProgress> uptadteProgress(@RequestBody CourseProgress courseProgress) {
        CourseProgress updatedProgress = courseProgressService.updateProgress(courseProgress);
        return ResponseEntity.ok(updatedProgress);
    }

    @GetMapping("/{userId}/{courseId}")
    public ResponseEntity<CourseProgress> getProgress(
            @PathVariable Long userId,
            @PathVariable String courseId) {
        CourseProgress progress = courseProgressService.getProgressByUserIdAndCourseId(userId, courseId);
        if (progress == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(progress);
    }
}
