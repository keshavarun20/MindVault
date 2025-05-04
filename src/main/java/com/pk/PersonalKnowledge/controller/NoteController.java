package com.pk.PersonalKnowledge.controller;

import com.pk.PersonalKnowledge.dto.CategoryDetailsDTO;
import com.pk.PersonalKnowledge.dto.CategoryNameDTO;
import com.pk.PersonalKnowledge.dto.NoteRequestDTO;
import com.pk.PersonalKnowledge.model.Category;
import com.pk.PersonalKnowledge.model.Note;
import com.pk.PersonalKnowledge.service.CategoryService;
import com.pk.PersonalKnowledge.service.NoteService;
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

    public NoteController(CategoryService categoryService, NoteService noteService) {
        this.categoryService = categoryService;
        this.noteService = noteService;
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

    @GetMapping("/api/{name}")
    public ResponseEntity<CategoryDetailsDTO> getCategoryDetailsByName(@PathVariable String name){
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
    public ResponseEntity<Map<String, Object>> getNoteById(@PathVariable UUID id){
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @GetMapping("/api/topics/{id}")
    public ResponseEntity<Map<String, Object>> getTitleAndNotesById(@PathVariable UUID id){
        return ResponseEntity.ok(noteService.getTitleAndNotesById(id));
    }


}
