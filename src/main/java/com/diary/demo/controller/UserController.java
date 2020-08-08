package com.diary.demo.controller;

import com.diary.demo.entity.Role;
import com.diary.demo.entity.User;
import com.diary.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(){
        return "authentication/registration";
    }

    @PostMapping("/registration")
    public String addNewUser(User user, Model model){
        User username = userRepository.findByUsername(user.getUsername());
        if (username != null){
            model.addAttribute("message", "User exists!");
            return "authentication/registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }
}