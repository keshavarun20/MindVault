package com.pk.PersonalKnowledge.service;


import com.pk.PersonalKnowledge.model.Topic;
import com.pk.PersonalKnowledge.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<String> getAllTopicTitles() {
        return topicRepository.findAll()
                .stream()
                .map(Topic::getTitle)
                .collect(Collectors.toList());
    }
}
