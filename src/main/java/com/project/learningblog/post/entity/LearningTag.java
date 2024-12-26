package com.project.learningblog.post.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "learning_tags")
@Getter
public class LearningTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<LearningPost> posts;

}
