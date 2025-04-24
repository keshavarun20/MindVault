package com.pk.PersonalKnowledge.service;

import com.pk.PersonalKnowledge.model.Category;
import com.pk.PersonalKnowledge.model.Note;
import com.pk.PersonalKnowledge.model.Tag;
import com.pk.PersonalKnowledge.model.Topic;
import com.pk.PersonalKnowledge.repository.CategoryRepository;
import com.pk.PersonalKnowledge.repository.NoteRepository;
import com.pk.PersonalKnowledge.repository.TagRepository;
import com.pk.PersonalKnowledge.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final TagRepository tagRepository;
    private final TopicRepository topicRepository;
    private final CategoryRepository categoryRepository;

    public NoteService(NoteRepository noteRepository, TagRepository tagRepository, TopicRepository topicRepository, CategoryRepository categoryRepository) {
        this.noteRepository = noteRepository;
        this.tagRepository = tagRepository;
        this.topicRepository = topicRepository;
        this.categoryRepository = categoryRepository;
    }

    public void createNote(String name, String description, String title, String content, List<String> tagNames) {
        // Check if a category exists or create a new category
        Category category = categoryRepository.findByName(name)
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(name);
                    newCategory.setDescription(description);
                    return categoryRepository.save(newCategory);
                });

        // Create and save the topic under the category
        Topic topic = new Topic();
        topic.setTitle(title);
        topic.setCategory(category);
        topicRepository.save(topic);

        // Create or find tags and add them to a set
        Set<Tag> tags = new HashSet<>();
        for (String tagName : tagNames) {
            Tag tag = tagRepository.findByName(tagName)
                    .orElseGet(() -> {
                        Tag newTag = new Tag();
                        newTag.setName(tagName);
                        return tagRepository.save(newTag);
                    });
            tags.add(tag);
        }

        // Create the note and save it
        Note note = new Note();
        note.setContent(content);
        note.setTags(tags);
        note.setTopic(topic);
        noteRepository.save(note);
    }

}
