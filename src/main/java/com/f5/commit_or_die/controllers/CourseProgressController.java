package com.f5.commit_or_die.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.f5.commit_or_die.model.CourseProgress;
import com.f5.commit_or_die.services.CourseProgressService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/course-progress")
public class CourseProgressController {

    private CourseProgressService courseProgressService;

    public CourseProgressController(CourseProgressService courseProgressService) {
        this.courseProgressService = courseProgressService;
    }

    @PostMapping("/create")
    public ResponseEntity<CourseProgress> createProgress(@RequestBody CourseProgress courseProgress) {
        CourseProgress createdProgress = courseProgressService.createProgress(courseProgress);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProgress);
    }

    @PutMapping("/update")
    public ResponseEntity<CourseProgress> updateProgress(@RequestBody CourseProgress courseProgress) {
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
