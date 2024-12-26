package com.project.learningblog.post.entity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "learning_post_images")
@Getter
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

