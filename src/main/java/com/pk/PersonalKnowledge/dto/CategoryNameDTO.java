package com.pk.PersonalKnowledge.dto;

import lombok.Data;

@Data
public class CategoryNameDTO {
    private String name;

    public CategoryNameDTO(String name) {
        this.name = name;
    }
}


