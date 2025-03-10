package com.f5.commit_or_die.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class CommentTest {

    @Test
    void testGetId() {
        Comment comment = new Comment(1L, "your comment", new Date(), 5, "resourceId", new User());
        assertEquals(1L, comment.getId());
    }

    @Test
    void testSetId() {
        Comment comment = new Comment();
        comment.setId(1L);
        assertEquals(1L, comment.getId());
    }

    @Test
    void testGetContent() {
        Comment comment = new Comment(1L, "your comment", new Date(), 5, "resourceId", new User());
        assertEquals("your comment", comment.getContent());
    }

    @Test
    void testSetContent() {
        Comment comment = new Comment();
        comment.setContent("your comment");
        assertEquals("your comment", comment.getContent());

    }

    @Test
    void testGetPublicationDate() {
        Comment comment = new Comment(1L, "content", new Date(), 5, "resourceId", new User());
        assertEquals(new Date(), comment.getPublicationDate());
    }

    @Test
    void testSetPublicationDate() {
        Comment comment = new Comment();
        Date date = new Date();
        comment.setPublicationDate(date);
        assertEquals(date, comment.getPublicationDate());

    }

    @Test
    void testGetRating() {
        Comment comment = new Comment(1L, "content", new Date(), 5, "resourceId", new User());
        assertEquals(5, comment.getRating());
    }

    @Test
    void testSetRating() {
        Comment comment = new Comment();
        comment.setRating(5);
        assertEquals(5, comment.getRating());
    }

    @Test
    void testGetResourceId() {
        Comment comment = new Comment(1L, "content", new Date(), 5, "resourceId", new User());
        assertEquals("resourceId", comment.getResourceId());
    }

    @Test
    void testSetResourceId() {
        Comment comment = new Comment();
        comment.setResourceId("resourceId");
        assertEquals("resourceId", comment.getResourceId());
    }

    @Test
    void testGetUser() {
        User user = new User();
        Comment comment = new Comment(1L, "content", new Date(), 5, "resourceId", user);
        assertEquals(user, comment.getUser());

    }

    @Test
    void testSetUser() {
        User user = new User();
        Comment comment = new Comment();
        comment.setUser(user);
        assertEquals(user, comment.getUser());

    }
}
