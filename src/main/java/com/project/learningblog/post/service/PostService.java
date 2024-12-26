package com.project.learningblog.post.service;

import com.project.learningblog.post.controller.dto.request.CreatePostRequest;
import com.project.learningblog.post.controller.dto.response.PostResponse;
import com.project.learningblog.post.entity.LearningPost;
import com.project.learningblog.post.repository.PostRepository;
import com.project.learningblog.post.service.dto.command.CreatePostCommand;
import com.project.learningblog.post.service.dto.command.UpdatePostCommand;
import com.project.learningblog.post.service.dto.result.PostResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    
    public List<PostResult> findAllPosts() {
    return postRepository.findAll().stream()
            .map(LearningPost::toResult)
            .toList();
    }

    public PostResult findPostById(long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("값이 존재하지 않습니다"))
                .toResult();
    }

    public void createPost(CreatePostCommand createPostCommand) {
        postRepository.save(LearningPost.of(createPostCommand));
    }

    public void updatePostById(UpdatePostCommand updatePostCommand) {
    }

    public void deletePostById(long id) {
    }
}
