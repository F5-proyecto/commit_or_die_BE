package com.f5.commit_or_die.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.f5.commit_or_die.model.Comment;
import com.f5.commit_or_die.repository.CommentRepository;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetComments() {
        Comment comment1 = new Comment();
        comment1.setId(1L);
        comment1.setResourceId("resource1");
        comment1.setContent("Comment 1");

        Comment comment2 = new Comment();
        comment2.setId(2L);
        comment2.setResourceId("resource1");
        comment2.setContent("Comment 2");

        List<Comment> mockComments = Arrays.asList(comment1, comment2);

        when(commentRepository.findByResourceId("resource1")).thenReturn(mockComments);

        List<Comment> result = commentService.getComments("resource1");

        assertEquals(2, result.size());
        assertEquals("Comment 1", result.get(0).getContent());
        assertEquals("Comment 2", result.get(1).getContent());

        verify(commentRepository, times(1)).findByResourceId("resource1");
    }

    @Test
    void testCreateComment() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setResourceId("resource1");
        comment.setContent("New Comment");

        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        Comment savedComment = commentService.createComment(comment);

        assertNotNull(savedComment);
        assertEquals("New Comment", savedComment.getContent());
        assertEquals("resource1", savedComment.getResourceId());

        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void testUpdateComment() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setResourceId("resource1");
        comment.setContent("Updated Comment");

        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        Comment updatedComment = commentService.updateComment(comment);

        assertNotNull(updatedComment);
        assertEquals("Updated Comment", updatedComment.getContent());

        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void testDeleteComment() {
        Long commentId = 1L;

        doNothing().when(commentRepository).deleteById(commentId);

        commentService.deleteComment(commentId);

        verify(commentRepository, times(1)).deleteById(commentId);
    }
}
