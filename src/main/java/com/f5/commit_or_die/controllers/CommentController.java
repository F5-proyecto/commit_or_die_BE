package com.f5.commit_or_die.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.f5.commit_or_die.model.Comment;
import com.f5.commit_or_die.services.CommentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments/{resourceId}")
    public List<Comment> getComments(@PathVariable String resourceId) {
        return commentService.getComments(resourceId);
    }

    @PostMapping("/comments")
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @PutMapping("/comments")
    public void updateComment( @RequestBody Comment comment) {
        commentService.updateComment(comment);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
