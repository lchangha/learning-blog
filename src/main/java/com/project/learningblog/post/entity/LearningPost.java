package com.project.learningblog.post.entity;


import com.project.learningblog.post.service.dto.command.CreatePostCommand;
import com.project.learningblog.post.service.dto.command.UpdatePostCommand;
import com.project.learningblog.post.service.dto.result.PostResult;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "learning_posts")
@Getter
@Builder
@AllArgsConstructor
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<LearningPostImage> images;

    @ManyToMany
    @JoinTable(
            name = "learning_post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<LearningTag> tags;


    public static LearningPost create(CreatePostCommand command,
                                      User user,
                                      List<LearningPostImage> learningPostImages) {
        return new LearningPostBuilder()
                .title(command.getTitle())
                .content(command.getContent())
                .user(user)
                .category(command.getCategory())
                .images(learningPostImages)
                .build();
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

    public LearningPost update(UpdatePostCommand command,
                                      List<LearningPostImage> learningPostImages) {
        return new LearningPostBuilder()
                .title(command.getTitle())
                .content(command.getContent())
                .category(command.getCategory())
                .images(learningPostImages)
                .build();
    }
}