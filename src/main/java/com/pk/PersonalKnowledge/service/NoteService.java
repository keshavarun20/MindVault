package com.pk.PersonalKnowledge.service;

import com.pk.PersonalKnowledge.repository.NoteRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

}
