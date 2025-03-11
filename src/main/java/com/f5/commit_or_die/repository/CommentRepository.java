package com.f5.commit_or_die.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.f5.commit_or_die.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

        List<Comment> findByResourceId(String resourceId);
}
