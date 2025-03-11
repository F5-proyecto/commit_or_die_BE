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

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

}
