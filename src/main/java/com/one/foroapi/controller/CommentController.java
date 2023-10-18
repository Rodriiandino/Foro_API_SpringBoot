package com.one.foroapi.controller;

import com.one.foroapi.domain.dto.comment.CommentDetailsDTO;
import com.one.foroapi.domain.dto.comment.CreateCommentDTO;
import com.one.foroapi.domain.dto.comment.UpdateCommentDTO;
import com.one.foroapi.domain.model.Comment;
import com.one.foroapi.domain.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/create")
    @Transactional
    public ResponseEntity<CommentDetailsDTO> createComment(@RequestBody @Valid CreateCommentDTO createCommentDTO) {
        Comment newComment = commentService.createComment(createCommentDTO);
        CommentDetailsDTO commentDetailsDTO = new CommentDetailsDTO(newComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDetailsDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<CommentDetailsDTO>> getAllComments(@PageableDefault(size = 5) Pageable pagination) {
        Page<CommentDetailsDTO> page = commentService.getAllComments(pagination).map(CommentDetailsDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDetailsDTO> getCommentById(@PathVariable Long commentId) {
        Comment comment = commentService.getCommentById(commentId);
        CommentDetailsDTO commentDetailsDTO = new CommentDetailsDTO(comment);
        return ResponseEntity.ok(commentDetailsDTO);
    }

    @PutMapping("/{commentId}")
    @Transactional
    public ResponseEntity<CommentDetailsDTO> updateComment(@PathVariable Long commentId, @RequestBody @Valid UpdateCommentDTO updateCommentDTO) {
        Comment comment = commentService.updateComment(commentId, updateCommentDTO);
        CommentDetailsDTO commentDetailsDTO = new CommentDetailsDTO(comment);
        return ResponseEntity.ok(commentDetailsDTO);
    }

    @DeleteMapping("/{commentId}")
    @Transactional
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long commentId) {
        commentService.deleteCommentById(commentId);
        return ResponseEntity.noContent().build();
    }

}
