package com.f5.commit_or_die.controllers;

import com.f5.commit_or_die.model.Comment;
import com.f5.commit_or_die.model.User;
import com.f5.commit_or_die.services.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private CommentService commentService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetComments() throws Exception {
        String resourceId = "123";
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("Great resource!");
        comment.setResourceId(resourceId);
        comment.setRating(5);
        User user = new User();
        user.setId(10L);
        comment.setUser(user);

        List<Comment> comments = Arrays.asList(comment);

        when(commentService.getComments(resourceId)).thenReturn(comments);

        mockMvc.perform(get("/comments/{resourceId}", resourceId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(comment.getId()))
                .andExpect(jsonPath("$[0].content").value(comment.getContent()))
                .andExpect(jsonPath("$[0].resourceId").value(comment.getResourceId()))
                .andExpect(jsonPath("$[0].rating").value(comment.getRating()))
                .andExpect(jsonPath("$[0].userId").value(comment.getUser().getId()));

        verify(commentService, times(1)).getComments(resourceId);
    }

    @Test
    void testCreateComment() throws Exception {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("Nice article!");
        comment.setResourceId("123");
        comment.setRating(4);
        User user = new User();
        user.setId(10L);
        comment.setUser(user);

        when(commentService.createComment(any(Comment.class))).thenReturn(comment);

        String jsonRequest = objectMapper.writeValueAsString(comment);

        mockMvc.perform(post("/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(comment.getId()))
                .andExpect(jsonPath("$.content").value(comment.getContent()))
                .andExpect(jsonPath("$.resourceId").value(comment.getResourceId()))
                .andExpect(jsonPath("$.rating").value(comment.getRating()))
                .andExpect(jsonPath("$.userId").value(comment.getUser().getId()));

        verify(commentService, times(1)).createComment(any(Comment.class));
    }

    @Test
    void testUpdateComment() throws Exception {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("Updated content");
        comment.setResourceId("123");
        comment.setRating(5);
        User user = new User();
        user.setId(10L);
        comment.setUser(user);

        when(commentService.updateComment(any(Comment.class))).thenReturn(comment);

        String jsonRequest = objectMapper.writeValueAsString(comment);

        mockMvc.perform(put("/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(comment.getId()))
                .andExpect(jsonPath("$.content").value(comment.getContent()))
                .andExpect(jsonPath("$.resourceId").value(comment.getResourceId()))
                .andExpect(jsonPath("$.rating").value(comment.getRating()))
                .andExpect(jsonPath("$.userId").value(comment.getUser().getId()));

        verify(commentService, times(1)).updateComment(any(Comment.class));
    }

    @Test
    void testDeleteComment() throws Exception {
        Long commentId = 1L;

        doNothing().when(commentService).deleteComment(commentId);

        mockMvc.perform(delete("/comments/{id}", commentId))
                .andExpect(status().isNoContent());

        verify(commentService, times(1)).deleteComment(commentId);
    }
}
