package com.diary.demo.controller;

import com.diary.demo.entity.Role;
import com.diary.demo.entity.User;
import com.diary.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import java.util.Collections;
import java.util.regex.Pattern;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Pattern PASSWORD_RULE_USER = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +                         // a digit must occur at least once
                    "(?=.*[A-Z])" +                         // an upper case letter must occur at least once
                    ".{8,}" +                               // 8 chars minimum
                    "$");

//    private static final Pattern USERNAME_RULE_USER = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
//            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

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
        String pass = user.getPassword();
        if(PASSWORD_RULE_USER.matcher(user.getPassword()).matches() &&
//                USERNAME_RULE_USER.matcher(user.getUsername()).matches()
        user.getUsername().length()>3) {
            user.setPassword(passwordEncoder.encode(pass));
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);
            return "redirect:/login";
        }else{
            model.addAttribute("shortUorP", "Your username or password is too short!");
            return "authentication/registration";
        }
    }
}
