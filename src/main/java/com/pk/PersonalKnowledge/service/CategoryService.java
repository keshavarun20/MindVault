package com.pk.PersonalKnowledge.service;

import com.pk.PersonalKnowledge.dto.CategoryNameDTO;
import com.pk.PersonalKnowledge.model.Category;
import com.pk.PersonalKnowledge.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(String name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        return categoryRepository.save(category);
    }

    public List<CategoryNameDTO> getAllCategoryNames() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryNameDTO(category.getName()))
                .collect(Collectors.toList());
    }
}
