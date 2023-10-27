package com.one.foroapi.controller;

import com.one.foroapi.domain.dto.topic.CreateTopicDTO;
import com.one.foroapi.domain.dto.topic.TopicDetailsDTO;
import com.one.foroapi.domain.dto.topic.UpdateTopicDTO;
import com.one.foroapi.domain.model.Topic;
import com.one.foroapi.domain.service.TopicService;
import com.one.foroapi.infra.exceptions.CustomErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topics")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Topics", description = "API for managing forum topics. Topics are the main categories of the forum.")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping("/create")
    @Transactional
    @Operation(
            summary = "Create a new topic",
            description = "Create a new topic with a title, a message, a category id and a user id"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Topic created successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicDetailsDTO.class)
            )
    )
    public ResponseEntity<TopicDetailsDTO> createTopic(@RequestBody @Valid CreateTopicDTO createTopicDTO) {
        Topic newTopic = topicService.createTopic(createTopicDTO);
        TopicDetailsDTO topicDetailsDTO = new TopicDetailsDTO(newTopic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicDetailsDTO);
    }

    @GetMapping("all")
    @Operation(
            summary = "Get All Topics",
            description = "Retrieve a list of all forum topics."
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of forum topics.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicDetailsDTO.class)
            )
    )
    public ResponseEntity<Page<TopicDetailsDTO>> getAllTopics(@PageableDefault(size = 5) Pageable pagination) {
        Page<TopicDetailsDTO> page = topicService.getAllTopics(pagination).map(TopicDetailsDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{topicId}")
    @Operation(
            summary = "Get Topic by Id",
            description = "Retrieve a topic by its id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Topic retrieved successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicDetailsDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Topic not found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)
            )
    )
    public ResponseEntity<TopicDetailsDTO> getTopicById(@PathVariable Long topicId) {
        Topic topic = topicService.getTopicById(topicId);
        TopicDetailsDTO topicDetailsDTO = new TopicDetailsDTO(topic);
        return ResponseEntity.ok(topicDetailsDTO);
    }

    @PutMapping("/{topicId}")
    @Transactional
    @Operation(
            summary = "Update a topic by id",
            description = "Update a topic by its id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Topic updated successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicDetailsDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Topic not found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)
            )
    )
    public ResponseEntity<TopicDetailsDTO> updateTopic(@PathVariable Long topicId,@RequestBody @Valid UpdateTopicDTO updateTopicDTO) {
        Topic topic = topicService.updateTopic(topicId, updateTopicDTO);
        TopicDetailsDTO topicDetailsDTO = new TopicDetailsDTO(topic);
        return ResponseEntity.ok(topicDetailsDTO);
    }

    @DeleteMapping("/{topicId}")
    @Transactional
    @Operation(
            summary = "Delete a topic by id",
            description = "Delete a topic by its id."
    )
    @ApiResponse(
            responseCode = "204",
            description = "Topic deleted successfully."
    )
    @ApiResponse(
            responseCode = "404",
            description = "Topic not found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)
            )
    )
    public ResponseEntity<Void> deleteLogicalTopicById(@PathVariable Long topicId) {
        topicService.deleteLogicalTopicById(topicId);
        return ResponseEntity.noContent().build();
    }
}
