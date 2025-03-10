package com.f5.commit_or_die.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CourseProgressTest {
    private CourseProgress courseProgress;

    @BeforeEach
    public void setUp() {
        courseProgress = new CourseProgress(1L, "course123", 75.0, 10, "user1");
    }

    @Test
    public void testGetId() {
        assertEquals(1L, courseProgress.getId());
    }

    @Test
    public void testGetCourseId() {
        assertEquals("course123", courseProgress.getCourseId());
    }

    @Test
    public void testGetProgressPercentage() {
        assertEquals(75.0, courseProgress.getProgressPercentage(), 0.01);
    }

    @Test
    public void testGetCompletedLessonsCount() {
        assertEquals(10, courseProgress.getCompletedLessonsCount());
    }

    @Test
    public void testGetUser() {
        assertEquals("user1", courseProgress.getUser());
    }

    @Test
    public void testSetId() {
        courseProgress.setId(2L);
        assertEquals(2L, courseProgress.getId());
    }

    @Test
    public void testSetCourseId() {
        courseProgress.setCourseId("course456");
        assertEquals("course456", courseProgress.getCourseId());
    }

    @Test
    public void testSetProgressPercentage() {
        courseProgress.setProgressPercentage(80.0);
        assertEquals(80.0, courseProgress.getProgressPercentage(), 0.01);
    }

    @Test
    public void testSetCompletedLessonsCount() {
        courseProgress.setCompletedLessonsCount(15);
        assertEquals(15, courseProgress.getCompletedLessonsCount());
    }

    @Test
    public void testSetUser() {
        courseProgress.setUser("user2");
        assertEquals("user2", courseProgress.getUser());
    }
}
