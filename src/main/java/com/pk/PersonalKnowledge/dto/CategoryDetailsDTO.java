package com.pk.PersonalKnowledge.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDetailsDTO {

    private String name;
    private String description;
    private List<String> topics;

    public CategoryDetailsDTO(String name, String description, List<String> topics) {
        this.name = name;
        this.description = description;
        this.topics = topics;
    }
}
