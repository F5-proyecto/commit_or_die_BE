package com.f5.commit_or_die.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.f5.commit_or_die.model.Comment;

    @DataJpaTest
    class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void testSaveComment() {

        Comment comment = new Comment();
        comment.setResourceId("resource1");
        comment.setContent("comment1");

        Comment savedComment = commentRepository.save(comment);

        assertNotNull(savedComment);
        assertNotNull(savedComment.getId());
        assertEquals("comment1", savedComment.getContent());
        assertEquals("resource1", savedComment.getResourceId());
        }

    @Test
    void testFindByResourceId() {
        Comment comment1 = new Comment();
        comment1.setResourceId("resource1");
        comment1.setContent("comment1");

        Comment comment2 = new Comment();
        comment2.setResourceId("resource1");
        comment2.setContent("comment2");

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        List<Comment> comments = commentRepository.findByResourceId("resource1");

        assertEquals(2, comments.size());
        assertEquals("comment1", comments.get(0).getContent());
        assertEquals("comment2", comments.get(1).getContent());
        }

        @Test
        void testDeleteComment() {
        Comment comment = new Comment();
        comment.setResourceId("resource1");
        comment.setContent("comment1");

        Comment savedComment = commentRepository.save(comment);
        Long commentId = savedComment.getId();

        commentRepository.deleteById(commentId);

        Comment deletedComment = commentRepository.findById(commentId).orElse(null);
        assertNull(deletedComment);
        
        }
    
}