package com.one.foroapi.controller;

import com.one.foroapi.domain.dto.post.CreatePostDTO;
import com.one.foroapi.domain.model.Post;
import com.one.foroapi.domain.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping("/create")
    @Transactional
    public ResponseEntity<Post> createPost(@RequestBody @Valid CreatePostDTO createPostDTO) {
        Post newPost = postService.createPost(createPostDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }



}
