package com.diary.demo.controller;

import com.diary.demo.entity.Message;
import com.diary.demo.entity.Note;
import com.diary.demo.service.MessageService;
import com.diary.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {
    @Autowired
    private NoteService noteService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/send/{id}")
    public String send(@PathVariable Integer id, Model model) {
        Note note = noteService.getNoteById(id);
        model.addAttribute("note", note);
        return "parts/send";
    }
    @PostMapping("/sendNote")
    public String sendNote(@RequestParam String note,
                           @RequestParam String email) {
        Message message = new Message();
        message.setMsg(note);
        message.setEmail(email);
        messageService.sendMessage(message);
        return "redirect:/";
    }
}
