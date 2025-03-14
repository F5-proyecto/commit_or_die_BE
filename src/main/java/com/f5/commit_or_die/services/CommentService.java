package com.f5.commit_or_die.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.f5.commit_or_die.model.Comment;
import com.f5.commit_or_die.repository.CommentRepository;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments(String resourceId) {
        return commentRepository.findByResourceId(resourceId);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) {
         return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

}
