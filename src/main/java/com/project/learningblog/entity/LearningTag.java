package com.project.learningblog.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "learning_tags")
public class LearningTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<LearningPost> posts;

}
