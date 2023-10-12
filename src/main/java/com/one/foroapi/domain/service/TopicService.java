package com.one.foroapi.domain.service;

import com.one.foroapi.domain.dto.topic.CreateTopicDTO;
import com.one.foroapi.domain.model.Category;
import com.one.foroapi.domain.model.Topic;
import com.one.foroapi.domain.model.User;
import com.one.foroapi.domain.repository.CategoryRepository;
import com.one.foroapi.domain.repository.TopicRepository;
import com.one.foroapi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            topic.setUser_id(user);
        }

        if (createTopicDTO.categoryId() != null) {
            Category category = categoryRepository.findById(createTopicDTO.categoryId()).orElse(null);
            topic.setCategory_id(category);
        }

        return topicRepository.save(topic);
    }
}

