package com.one.foroapi.controller;

import com.one.foroapi.domain.dto.topic.CreateTopicDTO;
import com.one.foroapi.domain.dto.topic.TopicDetailsDTO;
import com.one.foroapi.domain.model.Topic;
import com.one.foroapi.domain.service.TopicService;
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
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<TopicDetailsDTO> createTopic(@RequestBody @Valid CreateTopicDTO createTopicDTO) {
        Topic newTopic = topicService.createTopic(createTopicDTO);
        TopicDetailsDTO topicDetailsDTO = new TopicDetailsDTO(newTopic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicDetailsDTO);
    }

    @GetMapping("all")
    public ResponseEntity<Page<TopicDetailsDTO>> getAllTopics(@PageableDefault(size = 5) Pageable pagination) {
        Page<TopicDetailsDTO> page = topicService.getAllTopics(pagination).map(TopicDetailsDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{topicId}")
    public ResponseEntity<TopicDetailsDTO> getTopicById(@PathVariable Long topicId) {
        Topic topic = topicService.getTopicById(topicId);
        TopicDetailsDTO topicDetailsDTO = new TopicDetailsDTO(topic);
        return ResponseEntity.ok(topicDetailsDTO);
    }
}
