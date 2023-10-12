package com.one.foroapi.domain.repository;

import com.one.foroapi.domain.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
