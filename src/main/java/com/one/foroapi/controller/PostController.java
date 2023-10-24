package com.one.foroapi.controller;

import com.one.foroapi.domain.dto.post.CreatePostDTO;
import com.one.foroapi.domain.dto.post.PostDetailsDTO;
import com.one.foroapi.domain.dto.post.UpdatePostDTO;
import com.one.foroapi.domain.model.Post;
import com.one.foroapi.domain.service.PostService;
import com.one.foroapi.infra.exceptions.CustomErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "Posts", description = "API for managing forum posts. Posts are the main messages that users post in the forum.")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping("/create")
    @Transactional
    @Operation(
            summary = "Create a new post",
            description = "Create a new post with a title, a message, a topic id and a user id"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Post created successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDetailsDTO.class)
            )
    )
    public ResponseEntity<PostDetailsDTO> createPost(@RequestBody @Valid CreatePostDTO createPostDTO) {
        Post newPost = postService.createPost(createPostDTO);
        PostDetailsDTO postDetailsDTO = new PostDetailsDTO(newPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(postDetailsDTO);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get All Posts",
            description = "Retrieve a list of all forum posts."
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of forum posts.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDetailsDTO.class)
            )
    )
    public ResponseEntity<Page<PostDetailsDTO>> getAllPosts(@PageableDefault(size = 5) Pageable pagination) {
        Page<PostDetailsDTO> page = postService.getAllPosts(pagination).map(PostDetailsDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{postId}")
    @Operation(
            summary = "Get a post by id",
            description = "Retrieve a post by its id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Post found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDetailsDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Post not found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)
            )
    )
    public ResponseEntity<PostDetailsDTO> getPostById(@PathVariable Long postId) {
        Post post = postService.getPostById(postId);
        PostDetailsDTO postDetailsDTO = new PostDetailsDTO(post);
        return ResponseEntity.ok(postDetailsDTO);
    }

    @PutMapping("/{postId}")
    @Transactional
    @Operation(
            summary = "Update a post by id",
            description = "Update a post by its id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Post updated successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDetailsDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Post not found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)
            )
    )
    public ResponseEntity<PostDetailsDTO> updatePostById(@PathVariable Long postId, @RequestBody @Valid UpdatePostDTO updatePost) {
        Post post = postService.updatePost(postId, updatePost);
        PostDetailsDTO postDetailsDTO = new PostDetailsDTO(post);
        return ResponseEntity.ok(postDetailsDTO);
    }

    @DeleteMapping("/{postId}")
    @Transactional
    @Operation(
            summary = "Delete a post by id",
            description = "Delete a post by its id."
    )
    @ApiResponse(
            responseCode = "204",
            description = "Post deleted successfully.",
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(
            responseCode = "404",
            description = "Post not found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)
            )
    )
    public ResponseEntity<Void> deletePostById(@PathVariable Long postId) {
        postService.deletePostById(postId);
        return ResponseEntity.noContent().build();
    }


}
