package com.dhl.virtual.controller;

import com.dhl.virtual.model.UserModel;
import com.dhl.virtual.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "user_form";
    }

    @PostMapping("/users")
    public String saveUser(@ModelAttribute UserModel user) {
        userRepository.save(user);
        return "redirect:/users/new?success";
    }
}