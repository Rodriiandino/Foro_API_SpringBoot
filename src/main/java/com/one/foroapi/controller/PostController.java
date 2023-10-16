package com.one.foroapi.controller;

import com.one.foroapi.domain.dto.post.CreatePostDTO;
import com.one.foroapi.domain.dto.post.PostDetailsDTO;
import com.one.foroapi.domain.model.Post;
import com.one.foroapi.domain.service.PostService;
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
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping("/create")
    @Transactional
    public ResponseEntity<PostDetailsDTO> createPost(@RequestBody @Valid CreatePostDTO createPostDTO) {
        Post newPost = postService.createPost(createPostDTO);
        PostDetailsDTO postDetailsDTO = new PostDetailsDTO(newPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(postDetailsDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<PostDetailsDTO>> getAllPosts(@PageableDefault(size = 5) Pageable pagination) {
        Page<PostDetailsDTO> page = postService.getAllPosts(pagination).map(PostDetailsDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailsDTO> getPostById(@PathVariable Long postId) {
        Post post = postService.getPostById(postId);
        PostDetailsDTO postDetailsDTO = new PostDetailsDTO(post);
        return ResponseEntity.ok(postDetailsDTO);
    }


}
