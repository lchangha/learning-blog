package com.project.learningblog.post.service;

import com.project.learningblog.post.entity.LearningPost;
import com.project.learningblog.post.entity.LearningPostImage;
import com.project.learningblog.post.entity.User;
import com.project.learningblog.post.image.ImageUploadManager;
import com.project.learningblog.post.repository.PostRepository;
import com.project.learningblog.post.repository.UserRepository;
import com.project.learningblog.post.service.dto.command.Command;
import com.project.learningblog.post.service.dto.command.CreatePostCommand;
import com.project.learningblog.post.service.dto.command.UpdatePostCommand;
import com.project.learningblog.post.service.dto.result.PostResult;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageUploadManager imageUploadManager;

    public List<PostResult> findAllPosts() {
        return postRepository.findAll().stream()
                .map(LearningPost::toResult)
                .toList();
    }

    public PostResult findPostById(long id) {
        return postRepository.findById(id)
                .orElseThrow(IllegalStateException::new)
                .toResult();
    }

    @Transactional
    public void createPost(CreatePostCommand command) {

        List<LearningPostImage> learningPostImages = imageUpload(command);

        User user = userRepository.getReferenceById(command.getUserId());
        LearningPost learningPost = LearningPost.create(command, user, learningPostImages);

        postRepository.save(learningPost);
    }

    public void updatePostById(UpdatePostCommand command) {

        List<LearningPostImage> learningPostImages = imageUpload(command);

        LearningPost learningPost = postRepository.findById(command.getPostId())
                .orElseThrow(EntityNotFoundException::new)
                .update(command, learningPostImages);

        postRepository.save(learningPost);
    }

    public void deletePostById(long id) {
        postRepository.deleteById(id);
    }

    private List<LearningPostImage> imageUpload(Command command) {
        if (!command.getImages().isEmpty()) {
            return command.getImages().stream()
                    .map(imageUploadManager::handleUpload)
                    .map(LearningPostImage::of)
                    .toList();
        } else {
            return new ArrayList<>();
        }
    }
}
