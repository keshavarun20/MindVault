package com.pk.PersonalKnowledge.dto;

import lombok.Data;

import java.util.List;

@Data
public class NoteRequestDTO {


        private String name;
        private String description;
        private String title;
        private String content;
        private List<String> tagNames;

}
