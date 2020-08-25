package com.diary.demo.controller;

import com.diary.demo.entity.Message;
import com.diary.demo.entity.Note;
import com.diary.demo.service.MessageService;
import com.diary.demo.service.NoteService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@Controller
public class MessageController {
    @Autowired
    private NoteService noteService;

    @Autowired
    private MessageService messageService;

    private static final Pattern EMAIL_RULE = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    @GetMapping("/send/{id}")
    public String send(@PathVariable Integer id, Model model) {
        Note note = noteService.getNoteById(id);
        model.addAttribute("note", note);
        return "parts/send";
    }

    @PostMapping("/sendNote")
    public String sendNote(@RequestParam("id") Integer id, @RequestParam("email") String email, Model model) {
        Note note = noteService.getNoteById(id);
        String msg = note.getNote();
        String username = note.getAuthor().getUsername();
        Message message = new Message();
        message.setUsername(username);
        message.setMsg(msg);
        message.setEmail(email);
        if(EMAIL_RULE.matcher(message.getEmail()).matches()){
        messageService.sendMessage(message);
        return "redirect:/notes";
        }
        else {
            model.addAttribute("invalid", "Invalid email!");
            return "parts/send";
        }
    }
}
