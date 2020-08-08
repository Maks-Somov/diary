package com.diary.demo.repository;

import com.diary.demo.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findAllByOrderByDateAsc();
    List<Note> findAllByOrderByDateDesc();
}
