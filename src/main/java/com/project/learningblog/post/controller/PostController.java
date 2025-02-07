package com.project.learningblog.post.controller;

import com.project.learningblog.post.controller.dto.ApiResponse;
import com.project.learningblog.post.controller.dto.request.CreatePostRequest;
import com.project.learningblog.post.controller.dto.request.UpdatePostRequest;
import com.project.learningblog.post.controller.dto.response.PostResponse;
import com.project.learningblog.post.model.ImageFile;
import com.project.learningblog.post.service.PostService;
import com.project.learningblog.post.service.dto.command.CreatePostInput;
import com.project.learningblog.post.service.dto.command.UpdatePostInput;
import com.project.learningblog.post.service.dto.result.PostResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

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
        CreatePostInput command = createRequestToCommand(createPostRequest);
        postService.createPost(command);
        return ApiResponse.success(null);
    }

    @PutMapping("posts/{id}")
    public ApiResponse<PostResponse> updatePost(@PathVariable long id, @ModelAttribute UpdatePostRequest updatePostRequest) {
        UpdatePostInput command = updateRequestToCommand(id, updatePostRequest);
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


    private CreatePostInput createRequestToCommand(CreatePostRequest createPostRequest) {
        return new CreatePostInput(
                createPostRequest.getTitle(),
                createPostRequest.getUserId(),
                createPostRequest.getContent(),
                createPostRequest.getCategory(),
                createPostRequest.getTags(),
                convertToImageFiles(createPostRequest.getImages())
        );
    }


    private UpdatePostInput updateRequestToCommand(long userId, UpdatePostRequest updatePostRequest) {
        return new UpdatePostInput(
                updatePostRequest.getTitle(),
                userId,
                updatePostRequest.getContent(),
                updatePostRequest.getCategory(),
                updatePostRequest.getTags(),
                convertToImageFiles(updatePostRequest.getImages())
        );
    }

    private ImageFile convertToImageFile(MultipartFile file) {
        try {
            return new ImageFile(
                    file.getOriginalFilename(),
                    file.getInputStream(),
                    file.getContentType(),
                    file.getSize()
            );
        } catch (IOException e) {
            throw new IllegalArgumentException("파일 변환 중 오류 발생", e);
        }
    }

    private List<ImageFile> convertToImageFiles(List<MultipartFile> files) {
        return files.stream()
                .map(this::convertToImageFile)
                .toList();
    }



}
