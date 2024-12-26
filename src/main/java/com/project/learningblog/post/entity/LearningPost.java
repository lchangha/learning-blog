package com.project.learningblog.post.entity;


import com.project.learningblog.post.service.dto.command.CreatePostCommand;
import com.project.learningblog.post.service.dto.result.PostResult;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "learning_posts")
@Getter
public class LearningPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column()
    private String title;

    @Column()
    private String content;

    @Column()
    private String category;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LearningPostImage> images;

    @ManyToMany
    @JoinTable(
            name = "learning_post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<LearningTag> tags;


    public static LearningPost of(CreatePostCommand createPostCommand) {
        return new LearningPost(
                createPostCommand.
        );
    }


    public PostResult toResult() {
        List<String> tags = this.tags.stream()
                .map(LearningTag::getName)
                .toList();

        List<String> paths = images.stream()
                .map(LearningPostImage::getPath)
                .toList();

        return new PostResult(title, user.getUsername(), content, category, paths, tags);
    }

}