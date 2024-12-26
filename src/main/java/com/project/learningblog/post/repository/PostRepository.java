package com.project.learningblog.post.repository;

import com.project.learningblog.post.entity.LearningPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<LearningPost, Long> {

}
