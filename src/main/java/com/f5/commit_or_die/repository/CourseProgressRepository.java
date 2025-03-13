package com.f5.commit_or_die.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.f5.commit_or_die.model.CourseProgress;

@Repository
public interface CourseProgressRepository extends JpaRepository<CourseProgress, Long> {
    CourseProgress findByUser_IdAndCourseId(Long userId, String courseId);
}
