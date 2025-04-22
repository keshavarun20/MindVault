package com.pk.PersonalKnowledge.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Topic {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Note> notes = new ArrayList<>();

    private Date createdAt = new Date();
}
