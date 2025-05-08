package com.pk.PersonalKnowledge.service;

import com.pk.PersonalKnowledge.dto.CategoryDetailsDTO;
import com.pk.PersonalKnowledge.dto.CategoryNameDTO;
import com.pk.PersonalKnowledge.model.Category;
import com.pk.PersonalKnowledge.model.Topic;
import com.pk.PersonalKnowledge.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
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

    public CategoryDetailsDTO getCategoryDetailsByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));

        List<String> topicTitles = category.getTopics().stream()
                .map(Topic::getTitle)
                .collect(Collectors.toList());

        return new CategoryDetailsDTO(category.getName(), category.getDescription(), topicTitles);
    }

    public void deleteCategoryById(UUID id) {
        categoryRepository.deleteById(id);
    }
}
