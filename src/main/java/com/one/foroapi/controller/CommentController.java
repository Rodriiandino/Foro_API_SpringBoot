package com.one.foroapi.controller;

import com.one.foroapi.domain.dto.comment.CommentDetailsDTO;
import com.one.foroapi.domain.dto.comment.CreateCommentDTO;
import com.one.foroapi.domain.dto.comment.UpdateCommentDTO;
import com.one.foroapi.domain.model.Comment;
import com.one.foroapi.domain.service.CommentService;
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
@RequestMapping("/api/comments")
@Tag(name = "Comments", description = "API for managing forum comments. Comment are the messages that users post in the posts.")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/create")
    @Transactional
    @Operation(
            summary = "Create a new comment",
            description = "Create a new comment with a message and a post id"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Comment created successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDetailsDTO.class)
            )
    )
    public ResponseEntity<CommentDetailsDTO> createComment(@RequestBody @Valid CreateCommentDTO createCommentDTO) {
        Comment newComment = commentService.createComment(createCommentDTO);
        CommentDetailsDTO commentDetailsDTO = new CommentDetailsDTO(newComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDetailsDTO);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get All Comments",
            description = "Retrieve a list of all forum comments."
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of forum comments.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDetailsDTO.class)
            )
    )
    public ResponseEntity<Page<CommentDetailsDTO>> getAllComments(@PageableDefault(size = 5) Pageable pagination) {
        Page<CommentDetailsDTO> page = commentService.getAllComments(pagination).map(CommentDetailsDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{commentId}")
    @Operation(
            summary = "Get a comment by id",
            description = "Retrieve a comment by its id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Comment found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDetailsDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Comment not found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)
            )
    )
    public ResponseEntity<CommentDetailsDTO> getCommentById(@PathVariable Long commentId) {
        Comment comment = commentService.getCommentById(commentId);
        CommentDetailsDTO commentDetailsDTO = new CommentDetailsDTO(comment);
        return ResponseEntity.ok(commentDetailsDTO);
    }

    @PutMapping("/{commentId}")
    @Transactional
    @Operation(
            summary = "Update Comment",
            description = "Update a comment by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Comment updated successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDetailsDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Comment not found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)
            )
    )
    public ResponseEntity<CommentDetailsDTO> updateComment(@PathVariable Long commentId, @RequestBody @Valid UpdateCommentDTO updateCommentDTO) {
        Comment comment = commentService.updateComment(commentId, updateCommentDTO);
        CommentDetailsDTO commentDetailsDTO = new CommentDetailsDTO(comment);
        return ResponseEntity.ok(commentDetailsDTO);
    }

    @DeleteMapping("/{commentId}")
    @Transactional
    @Operation(
            summary = "Delete Comment",
            description = "Delete a comment by its ID."
    )
    @ApiResponse(
            responseCode = "204",
            description = "Comment deleted successfully.",
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(
            responseCode = "404",
            description = "Comment not found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)
            )
    )
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long commentId) {
        commentService.deleteCommentById(commentId);
        return ResponseEntity.noContent().build();
    }

}
