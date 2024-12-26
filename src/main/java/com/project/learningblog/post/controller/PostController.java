package com.project.learningblog.post.controller;

import com.project.learningblog.post.controller.dto.ApiResponse;
import com.project.learningblog.post.controller.dto.request.CreatePostRequest;
import com.project.learningblog.post.controller.dto.request.UpdatePostRequest;
import com.project.learningblog.post.controller.dto.response.PostResponse;
import com.project.learningblog.post.service.PostService;
import com.project.learningblog.post.service.dto.command.CreatePostCommand;
import com.project.learningblog.post.service.dto.command.UpdatePostCommand;
import com.project.learningblog.post.service.dto.result.PostResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("api/board")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("posts")
    public ApiResponse<List<PostResponse>> getAllPosts() {
        List<PostResponse> response = postService.findAllPosts().stream().map(this::resultToResponse).toList();
        return ApiResponse.success(response);
    }

    @GetMapping("posts/{id}")
    public ApiResponse<PostResponse> getPostById(@PathVariable long id) {
        return ApiResponse.success(resultToResponse(postService.findPostById(id))
        );
    }

    @PostMapping("posts")
    public ApiResponse<PostResponse> createPost(@ModelAttribute CreatePostRequest createPostRequest) {
        CreatePostCommand command = createRequestToCommand(createPostRequest);
        postService.createPost(command);
        return ApiResponse.success(null);
    }

    @PutMapping("posts/{id}")
    public ApiResponse<PostResponse> updatePost(@PathVariable long id, @ModelAttribute UpdatePostRequest updatePostRequest) {
        UpdatePostCommand command = updateRequestToCommand(id, updatePostRequest);
        postService.updatePostById(command);
        return ApiResponse.success(null);

    }

    @DeleteMapping("posts/{id}")
    public ApiResponse<PostResponse> deletePost(@PathVariable long id) {
        postService.deletePostById(id);
        return ApiResponse.success(null);
    }

    private PostResponse resultToResponse(PostResult postResult) {
        return new PostResponse(
                postResult.getTitle(),
                postResult.getAuthor(),
                postResult.getContent(),
                postResult.getCategory(),
                postResult.getTags(),
                postResult.getImagePaths()
        );
    }


    private CreatePostCommand createRequestToCommand(CreatePostRequest createPostRequest) {
        return new CreatePostCommand(
                createPostRequest.getTitle(),
                createPostRequest.getUserId(),
                createPostRequest.getContent(),
                createPostRequest.getCategory(),
                createPostRequest.getTags(),
                createPostRequest.getImages().stream()
                        .map(this::convertToBoxedBytes)
                        .toList()
        );
    }


    private UpdatePostCommand updateRequestToCommand(long userId, UpdatePostRequest updatePostRequest) {
        return new UpdatePostCommand(
                updatePostRequest.getTitle(),
                userId,
                updatePostRequest.getContent(),
                updatePostRequest.getCategory(),
                updatePostRequest.getTags(),
                updatePostRequest.getImages().stream()
                        .map(this::convertToBoxedBytes)
                        .toList()
        );
    }

    private Byte[] convertToBoxedBytes(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            return IntStream.range(0, bytes.length)
                    .map(i -> bytes[i] & 0xFF)
                    .boxed()
                    .toArray(Byte[]::new);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
