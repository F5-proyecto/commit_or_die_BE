package com.f5.commit_or_die.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.f5.commit_or_die.dto.CommentResponse;
import com.f5.commit_or_die.model.Comment;
import com.f5.commit_or_die.services.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable String resourceId) {
        List<Comment> comments = commentService.getComments(resourceId);
        List<CommentResponse> response = comments.stream()
                .map(this::mapToCommentResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(comment);
        CommentResponse response = mapToCommentResponse(createdComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<CommentResponse> updateComment(@Valid @RequestBody Comment comment) {
        Comment updatedComment = commentService.updateComment(comment);
        CommentResponse response = mapToCommentResponse(updatedComment);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    private CommentResponse mapToCommentResponse(Comment comment) {
        CommentResponse dto = new CommentResponse();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setResourceId(comment.getResourceId());
        dto.setRating(comment.getRating());
        dto.setUserId(comment.getUser().getId());
        return dto;
    }
}
