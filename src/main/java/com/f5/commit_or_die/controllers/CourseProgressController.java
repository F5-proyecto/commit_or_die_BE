package com.f5.commit_or_die.controllers;

import com.f5.commit_or_die.dto.CourseProgressResponse;
import com.f5.commit_or_die.model.CourseProgress;
import com.f5.commit_or_die.services.CourseProgressService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course-progress")
public class CourseProgressController {

    private final CourseProgressService courseProgressService;

    public CourseProgressController(CourseProgressService courseProgressService) {
        this.courseProgressService = courseProgressService;
    }

    @PostMapping("/create")
    public ResponseEntity<CourseProgressResponse> createProgress(@RequestBody CourseProgress courseProgress) {
        CourseProgress createdProgress = courseProgressService.createProgress(courseProgress);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToCourseProgressResponse(createdProgress));
    }

    @PutMapping("/update")
    public ResponseEntity<CourseProgressResponse> updateProgress(@Valid @RequestBody CourseProgress courseProgress) {
        CourseProgress updatedProgress = courseProgressService.updateProgress(courseProgress);
        return ResponseEntity.ok(mapToCourseProgressResponse(updatedProgress));
    }

    @GetMapping("/{userId}/{courseId}")
    public ResponseEntity<CourseProgressResponse> getProgress(
            @PathVariable Long userId,
            @PathVariable String courseId) {
        CourseProgress progress = courseProgressService.getProgressByUserIdAndCourseId(userId, courseId);
        return (progress != null) 
            ? ResponseEntity.ok(mapToCourseProgressResponse(progress)) 
            : ResponseEntity.notFound().build();
    }

    private CourseProgressResponse mapToCourseProgressResponse(CourseProgress progress) {
        return new CourseProgressResponse(
                progress.getId(),
                progress.getCourseId(),
                progress.getProgressPercentage(),
                progress.getCompletedLessonsCount(),
                progress.getUser().getId()
        );
    }
}
