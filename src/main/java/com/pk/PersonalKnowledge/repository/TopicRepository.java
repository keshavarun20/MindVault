package com.pk.PersonalKnowledge.repository;

import com.pk.PersonalKnowledge.model.Category;
import com.pk.PersonalKnowledge.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {

    Optional<Topic> findByTitleAndCategory(String title, Category category);

}
