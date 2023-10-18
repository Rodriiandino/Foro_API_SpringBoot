package com.one.foroapi.domain.service;

import com.one.foroapi.domain.dto.topic.CreateTopicDTO;
import com.one.foroapi.domain.dto.topic.UpdateTopicDTO;
import com.one.foroapi.domain.model.Category;
import com.one.foroapi.domain.model.Topic;
import com.one.foroapi.domain.model.User;
import com.one.foroapi.domain.repository.CategoryRepository;
import com.one.foroapi.domain.repository.TopicRepository;
import com.one.foroapi.domain.repository.UserRepository;
import com.one.foroapi.util.TimeLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public TopicService(
            TopicRepository topicRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository
    ) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public Topic createTopic(CreateTopicDTO createTopicDTO) {
        Topic topic = new Topic(createTopicDTO);

        if (createTopicDTO.userId() != null) {
            User user = userRepository.findById(createTopicDTO.userId()).orElse(null);
            topic.setUser(user);
        }

        if (createTopicDTO.categoryId() != null) {
            Category category = categoryRepository.findById(createTopicDTO.categoryId()).orElse(null);
            topic.setCategory(category);
        }

        return topicRepository.save(topic);
    }

    public Page<Topic> getAllTopics(Pageable pagination) {
        return topicRepository.findAll(pagination);
    }

    public Topic getTopicById(Long topicId) {
        return topicRepository.findById(topicId).orElse(null);
    }

    public void deleteLogicalTopicById(Long topicId) {
        Topic topic = getTopicById(topicId);
        topic.deleteLogical();
    }

    public Topic updateTopic(Long id ,UpdateTopicDTO updateTopicDTO) {
        Topic topic = getTopicById(id);

        if (!TimeLimit.isEditableWithinTimeLimit(topic.getCreated_at())) {
            throw new RuntimeException("The topic is not editable after 15 minutes of its creation dat time");
        }

        if (updateTopicDTO.title() != null) {
            topic.setTitle(updateTopicDTO.title());
        }

        if (updateTopicDTO.description() != null) {
            topic.setDescription(updateTopicDTO.description());
        }

        if (updateTopicDTO.categoryId() != null) {
            Category category = categoryRepository.findById(updateTopicDTO.categoryId()).orElse(null);
            topic.setCategory(category);
        }

        return topicRepository.save(topic);
    }
}

