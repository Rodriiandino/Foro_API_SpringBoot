package com.one.foroapi.controller;

import com.one.foroapi.domain.dto.topic.CreateTopicDTO;
import com.one.foroapi.domain.model.Topic;
import com.one.foroapi.domain.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Topic> createTopic(@RequestBody @Valid CreateTopicDTO createTopicDTO) {
        Topic newTopic = topicService.createTopic(createTopicDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTopic);
    }

}
