package com.diary.demo.controller;

import com.diary.demo.entity.Note;
import com.diary.demo.entity.User;
import com.diary.demo.service.NoteService;
import com.diary.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;

    private String sortDateMethod = "ASC";

    @GetMapping("/")
    public String index(Principal principal)
    {
        if(principal != null)
        {
            return "redirect:/notes";
        }
        return "begin";
    }

    @GetMapping("/notes")
    public String list(
//            @AuthenticationPrincipal User user,
            Principal principal,
            Model model) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        List<Note> notebook = filterAndSort(user);
        model.addAttribute("notes", notebook);
        model.addAttribute("sort", sortDateMethod);
        return "index";
    }
    @PostMapping("/newOnMain")
    public String addNewNoteOnMain(@RequestParam String note,
//                                   @AuthenticationPrincipal User user,
                                   Principal principal,
                                   Model model){
        User user = (User) userService.loadUserByUsername(principal.getName());
        if(note.length()>=1){
            noteService.saveNote(new Note(note, user));
        }
        return "redirect:/notes";
    }

    @GetMapping("/sort/{sortDate}")
    public String sortChoose(@PathVariable String sortDate) {
        sortDateMethod = sortDate;
        return "redirect:/notes";
    }

    @GetMapping("/new")
    public String newNote() {
        return "parts/new";
    }

    @PostMapping("/save")
    public String saveNote(@RequestParam String note,
//                           @AuthenticationPrincipal User user,
                           Principal principal,
                           Model model) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        if(note.length()>=1){
            noteService.saveNote(new Note(note, user));
            return "redirect:/notes";
        }else{
            model.addAttribute("shortNote", "Short note!");
            return  "parts/new";}
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Note note = noteService.getNoteById(id);
        model.addAttribute("note", note);
        return "parts/edit";
    }

    @PostMapping("/update")
    public String updateNote(@RequestParam Integer id, @RequestParam String note,
                             @RequestParam(value = "done", required = false) boolean done) {
            noteService.updateNote(id, note, done);
            return "redirect:/notes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        noteService.deleteNote(id);
        return "redirect:/notes";
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