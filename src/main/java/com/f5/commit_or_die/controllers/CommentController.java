package com.f5.commit_or_die.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.f5.commit_or_die.model.Comment;
import com.f5.commit_or_die.services.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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
    

    
}
