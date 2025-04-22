package com.pk.PersonalKnowledge.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Note {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToMany
    @JoinTable(
            name = "note_tag",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    private Date createdAt = new Date();
}
