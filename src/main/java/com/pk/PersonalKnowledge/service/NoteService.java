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

import java.util.*;
import java.util.stream.Collectors;

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

        // 2. Check if topic with given title exists under the same category
        Topic topic = topicRepository.findByTitleAndCategory(title, category)
                .orElseGet(() -> {
                    Topic newTopic = new Topic();
                    newTopic.setTitle(title);
                    newTopic.setCategory(category);
                    return topicRepository.save(newTopic);
                });

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

    public Map<String, Object> getNoteById(UUID id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note Not Found"));

        Map<String, Object> result = new HashMap<>();
        result.put("content", note.getContent());
        result.put("tags", note.getTags().stream().map(Tag::getName).toList());

        return result;
    }
    public Map<String, Object> getTitleAndNotesById(UUID id) {
        Topic topic= topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic Not Found"));

        Map<String, Object> result = new HashMap<>();
        result.put("title", topic.getTitle());

        List<Map<String, Object>> noteList = topic.getNotes().stream().map(note -> {
            Map<String, Object> noteData = new HashMap<>();
            noteData.put("content", note.getContent());
            noteData.put("tags", note.getTags().stream().map(Tag::getName).toList());
            return noteData;
        }).toList();

        result.put("notes", noteList);

        return result;
    }
}
