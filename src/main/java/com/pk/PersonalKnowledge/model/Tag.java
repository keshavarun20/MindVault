package com.pk.PersonalKnowledge.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.*;

@Entity
@Data
public class Tag {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Note> notes = new HashSet<>();
}

