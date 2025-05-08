package com.pk.PersonalKnowledge.controller;

import com.pk.PersonalKnowledge.dto.CategoryDetailsDTO;
import com.pk.PersonalKnowledge.dto.CategoryNameDTO;
import com.pk.PersonalKnowledge.dto.NoteRequestDTO;
import com.pk.PersonalKnowledge.dto.UpdateNoteRequestDTO;
import com.pk.PersonalKnowledge.model.Category;
import com.pk.PersonalKnowledge.service.CategoryService;
import com.pk.PersonalKnowledge.service.NoteService;
import com.pk.PersonalKnowledge.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class NoteController {

    private final CategoryService categoryService;
    private final NoteService noteService;
    private final TopicService topicService;

    public NoteController(CategoryService categoryService, NoteService noteService, TopicService topicService) {
        this.categoryService = categoryService;
        this.noteService = noteService;
        this.topicService = topicService;
    }

    @PostMapping("/api/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category.getName(), category.getDescription());

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/api/categories")
    public ResponseEntity<List<CategoryNameDTO>> getAllCategoryNames() {
        return ResponseEntity.ok(categoryService.getAllCategoryNames());
    }

    @GetMapping("/api/categories/{name}")
    public ResponseEntity<CategoryDetailsDTO> getCategoryDetailsByName(@PathVariable String name) {
        return ResponseEntity.ok(categoryService.getCategoryDetailsByName(name));
    }

    @PostMapping("/api/notes")
    public ResponseEntity<String> createNote(@RequestBody NoteRequestDTO request) {
        noteService.createNote(
                request.getName(),
                request.getDescription(),
                request.getTitle(),
                request.getContent(),
                request.getTagNames()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body("Note created successfully.");
    }

    @GetMapping("/api/notes/{id}")
    public ResponseEntity<Map<String, Object>> getNoteById(@PathVariable UUID id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @GetMapping("/api/topics/{id}")
    public ResponseEntity<Map<String, Object>> getTitleAndNotesById(@PathVariable UUID id) {
        return ResponseEntity.ok(noteService.getTitleAndNotesById(id));
    }

    @GetMapping("/api/topics")
    public ResponseEntity<List<String>> getAllTopicTitles() {
        return ResponseEntity.ok(topicService.getAllTopicTitles());
    }

    @PutMapping("/api/notes/{id}")
    public ResponseEntity<String> updateNote(
            @PathVariable UUID id,
            @RequestBody UpdateNoteRequestDTO request
    ) {
        noteService.updateNote(
                id,
                request.getTitle(),
                request.getContent(),
                request.getName(),
                request.getDescription(),
                request.getTagNames()
        );
        return ResponseEntity.ok("Note updated successfully");
    }

    @DeleteMapping("/api/notes/{id}")
    public ResponseEntity<String> deleteNoteById(@PathVariable UUID id) {
        boolean deleted = noteService.deleteNoteById(id);
        if (deleted) {
            return ResponseEntity.ok("Note deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/delete/tags")
    public ResponseEntity<String> deleteTagsByNames(@RequestBody List<String> tagNames) {
        noteService.deleteTagsByNames(tagNames);
        return ResponseEntity.ok("Tags deleted and unlinked from all notes");
    }

    @DeleteMapping("/api/topics/{id}")
    public ResponseEntity<String> deleteTopicById(@PathVariable UUID id) {
        topicService.deleteTopicById(id);
        return ResponseEntity.ok("Topic deleted");
    }

    @DeleteMapping("/api/categories/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable UUID id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok("Category deleted");
    }


}
