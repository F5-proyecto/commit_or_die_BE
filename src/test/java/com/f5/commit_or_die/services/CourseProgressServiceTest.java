package com.f5.commit_or_die.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.f5.commit_or_die.model.CourseProgress;
import com.f5.commit_or_die.repository.CourseProgressRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CourseProgressServiceTest {

    @Mock
    private CourseProgressRepository courseProgressRepository;

    @InjectMocks
    private CourseProgressService courseProgressService;

    private CourseProgress courseProgress;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        courseProgress = new CourseProgress();
        courseProgress.setId(1L);
        courseProgress.setCourseId("course123");
        courseProgress.setProgressPercentage(75.0);
        courseProgress.setCompletedLessonsCount(10);
    }

    @Test
    public void testCreateProgress() {
        when(courseProgressRepository.save(any(CourseProgress.class))).thenReturn(courseProgress);

        CourseProgress createdProgress = courseProgressService.createProgress(courseProgress);

        assertNotNull(createdProgress);
        assertEquals(courseProgress.getId(), createdProgress.getId());
        verify(courseProgressRepository, times(1)).save(courseProgress);
    }

    @Test
    public void testUpdateProgress() {
        when(courseProgressRepository.save(any(CourseProgress.class))).thenReturn(courseProgress);

        CourseProgress updatedProgress = courseProgressService.updateProgress(courseProgress);

        assertNotNull(updatedProgress);
        assertEquals(courseProgress.getId(), updatedProgress.getId());
        verify(courseProgressRepository, times(1)).save(courseProgress);
    }

    @Test
    public void testGetProgressByUserIdAndCourseId() {
        Long userId = 1L;
        String courseId = "course123";
        when(courseProgressRepository.findByUser_IdAndCourseId(userId, courseId)).thenReturn(courseProgress);

        CourseProgress foundProgress = courseProgressService.getProgressByUserIdAndCourseId(userId, courseId);

        assertNotNull(foundProgress);
        assertEquals(courseProgress.getId(), foundProgress.getId());
        assertEquals(courseProgress.getCourseId(), foundProgress.getCourseId());
        verify(courseProgressRepository, times(1)).findByUser_IdAndCourseId(userId, courseId);
    }
}
