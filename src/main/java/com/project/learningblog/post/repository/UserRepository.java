package com.project.learningblog.post.repository;

import com.project.learningblog.post.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
