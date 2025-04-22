package com.pk.PersonalKnowledge.controller;

import com.pk.PersonalKnowledge.dto.CategoryNameDTO;
import com.pk.PersonalKnowledge.model.Category;
import com.pk.PersonalKnowledge.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    private final CategoryService categoryService;

    public NoteController(CategoryService categoryService) {
        this.categoryService = categoryService;
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


}
