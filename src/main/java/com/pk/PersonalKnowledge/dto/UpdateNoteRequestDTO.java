package com.pk.PersonalKnowledge.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateNoteRequestDTO {
    private String title;
    private String content;
    private String name;
    private String description;
    private List<String> tagNames;
}
