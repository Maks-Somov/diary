package com.diary.demo.service;

import com.diary.demo.entity.Note;
import com.diary.demo.entity.User;

import java.util.List;

public interface NoteService {
    void saveNote(Note note);
    Note getNoteById(Integer id);
    void updateNote(Integer id, String note, boolean done);
    void deleteNote(Integer id);
    List<Note> findAll();
    List<Note> findAllByOrderByDateAsc();
    List<Note> findAllByOrderByDateDesc();
    List<Note> findAllByAuthorOrderByDateAsc(User user);
    List<Note> findAllByAuthorOrderByDateDesc(User user);
}
