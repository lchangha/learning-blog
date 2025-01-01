package com.project.learningblog.post.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "learning_post_images")
@Getter
@NoArgsConstructor
public class LearningPostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String path;

    public LearningPostImage(String path) {
        this.path = path;
    }


    public static LearningPostImage of(String path) {
        return new LearningPostImage(path);
    }
}

