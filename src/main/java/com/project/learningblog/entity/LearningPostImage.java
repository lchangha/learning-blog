package com.project.learningblog.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "learning_post_images")
public class LearningPostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private LearningPost post;

    @Column()
    private String path;

}

