package com.pk.PersonalKnowledge.repository;

import com.pk.PersonalKnowledge.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {

    Optional<Tag> findByName(String tagName);

    List<Tag> findByNameIn(List<String> names);

}
