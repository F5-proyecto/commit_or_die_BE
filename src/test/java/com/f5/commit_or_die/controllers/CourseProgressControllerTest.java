package com.f5.commit_or_die.controllers;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.f5.commit_or_die.model.CourseProgress;
import com.f5.commit_or_die.model.User;
import com.f5.commit_or_die.services.CourseProgressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CourseProgressController.class)
public class CourseProgressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private CourseProgressService courseProgressService;

    @InjectMocks
    private CourseProgressController courseProgressController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseProgressController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateProgress() throws Exception {
        CourseProgress courseProgress = new CourseProgress();
        courseProgress.setCourseId("course123");
        courseProgress.setProgressPercentage(50.0);
        courseProgress.setCompletedLessonsCount(5);
        courseProgress.setUser(new User(1L, "username", null, null, null, null));

        when(courseProgressService.createProgress(any(CourseProgress.class))).thenReturn(courseProgress);

        mockMvc.perform(post("/course-progress/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(courseProgress)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.courseId").value("course123"))
                .andExpect(jsonPath("$.progressPercentage").value(50.0))
                .andExpect(jsonPath("$.completedLessonsCount").value(5))
                .andExpect(jsonPath("$.user.id").value(1L));
    }

    @Test
    public void testUpdateProgress() throws Exception {
        CourseProgress courseProgress = new CourseProgress();
        courseProgress.setCourseId("course123");
        courseProgress.setProgressPercentage(75.0);
        courseProgress.setCompletedLessonsCount(10);
        courseProgress.setUser(new User(1L, "username", null, null, null, null));

        when(courseProgressService.updateProgress(any(CourseProgress.class))).thenReturn(courseProgress);

        mockMvc.perform(put("/course-progress/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(courseProgress)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId").value("course123"))
                .andExpect(jsonPath("$.progressPercentage").value(75.0))
                .andExpect(jsonPath("$.completedLessonsCount").value(10))
                .andExpect(jsonPath("$.user.id").value(1L));
    }

    @Test
    public void testGetProgress() throws Exception {
        CourseProgress courseProgress = new CourseProgress();
        courseProgress.setCourseId("course123");
        courseProgress.setProgressPercentage(100.0);
        courseProgress.setCompletedLessonsCount(20);
        courseProgress.setUser(new User(1L, "username", null, null, null, null));

        when(courseProgressService.getProgressByUserIdAndCourseId(eq(1L), eq("course123"))).thenReturn(courseProgress);

        mockMvc.perform(get("/course-progress/1/course123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId").value("course123"))
                .andExpect(jsonPath("$.progressPercentage").value(100.0))
                .andExpect(jsonPath("$.completedLessonsCount").value(20))
                .andExpect(jsonPath("$.user.id").value(1L));
    }

    @Test
    public void testGetProgressNotFound() throws Exception {
        when(courseProgressService.getProgressByUserIdAndCourseId(eq(1L), eq("course123"))).thenReturn(null);

        mockMvc.perform(get("/course-progress/1/course123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
