package com.diary.demo.repository;

import com.diary.demo.entity.Note;
import com.diary.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findAllByOrderByDateAsc();
    List<Note> findAllByOrderByDateDesc();
    List<Note> findAllByAuthorOrderByDateAsc(User user);
    List<Note> findAllByAuthorOrderByDateDesc(User user);
    List<Note> findByAuthor(User user);
}
