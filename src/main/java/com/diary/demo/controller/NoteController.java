package com.diary.demo.controller;

import com.diary.demo.entity.Note;
import com.diary.demo.entity.User;
import com.diary.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NoteController {
    @Autowired
    private NoteService noteService;

    private String sortDateMethod = "ASC";

    @GetMapping("/")
    public String hello(){
        return "parts/home";
    }

    @GetMapping("/index")
    public String list(@AuthenticationPrincipal User user, Model model) {
        List<Note> notebook = filterAndSort(user);
        model.addAttribute("notes", notebook);
        model.addAttribute("sort", sortDateMethod);
        return "index";
    }
    @PostMapping("/index")
    public String addNewNoteOnMain(@RequestParam String note, @AuthenticationPrincipal User user){
            noteService.saveNote(new Note(note, user));
        return "redirect:/index";
    }

    @GetMapping("/sort/{sortDate}")
    public String sortChoose(@PathVariable String sortDate) {
        sortDateMethod = sortDate;
        return "redirect:/index";
    }

    @GetMapping("/new")
    public String newNote() {
        return "parts/new";
    }

    @PostMapping("/save")
    public String updateNote(@RequestParam String note, @AuthenticationPrincipal User user) {
        if (note!=null) {
            noteService.saveNote(new Note(note, user));
            return "redirect:/index";
        }else return  "parts/new";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Note note = noteService.getNoteById(id);
        model.addAttribute("note", note);
        return "parts/edit";
    }

    @PostMapping("/update")
    public String saveNote(@RequestParam Integer id, @RequestParam String note,
                           @RequestParam(value = "done", required = false) boolean done) {
        noteService.updateNote(id, note, done);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        noteService.deleteNote(id);
        return "redirect:/index";
    }

    private List<Note> filterAndSort(User user) {
        List<Note> notebook = null;
        switch (sortDateMethod) {
            case "ASC":
                notebook = noteService.findAllByAuthorOrderByDateAsc(user);
                break;
            case "DESC":
                notebook = noteService.findAllByAuthorOrderByDateDesc(user);
                break;
        }
        return notebook;
    }
}