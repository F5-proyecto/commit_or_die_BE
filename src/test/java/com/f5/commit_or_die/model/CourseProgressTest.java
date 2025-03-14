package com.f5.commit_or_die.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CourseProgressTest {
    private CourseProgress courseProgress;

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setId(1L);
        courseProgress = new CourseProgress(1L, "course123", 75.0, 10, user);
    }

    @Test
    public void testEmptytConstructor() {
        CourseProgress emptyProgress = new CourseProgress();
        assertEquals(null, emptyProgress.getId());
        assertEquals(null, emptyProgress.getCourseId());
        assertEquals(0.0, emptyProgress.getProgressPercentage(), 0.01);
        assertEquals(0, emptyProgress.getCompletedLessonsCount());
        assertEquals(null, emptyProgress.getUser());
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
        User user = courseProgress.getUser();
        assertNotNull(user);
        assertEquals(1L, user.getId());
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
        User user2 = new User();
        user2.setId(2L);
        courseProgress.setUser(user2);
        assertEquals(user2, courseProgress.getUser());
    }
}
