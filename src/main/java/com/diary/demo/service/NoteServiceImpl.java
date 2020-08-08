package com.diary.demo.service;

import com.diary.demo.entity.Note;
import com.diary.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note getNoteById(Integer id) {
        return noteRepository.getOne(id);
    }

    @Override
    public void updateNote(Integer id, String note, boolean done) {
        Note update = noteRepository.getOne(id);
        update.setDone(done);
        update.setNote(note);
        noteRepository.save(update);
    }

    @Override
    public void deleteNote(Integer id) {
        noteRepository.deleteById(id);
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public List<Note> findAllByOrderByDateAsc() {
        return noteRepository.findAllByOrderByDateAsc();
    }

    @Override
    public List<Note> findAllByOrderByDateDesc() {
        return noteRepository.findAllByOrderByDateDesc();
    }

    @Override
    public void saveNote(Note note) {
        noteRepository.save(note);
    }


}
