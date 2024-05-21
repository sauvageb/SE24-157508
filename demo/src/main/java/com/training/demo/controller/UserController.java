package com.training.demo.controller;

import com.training.demo.DemoAutowire;
import com.training.demo.repository.User;
import com.training.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final DemoAutowire autowire;

    public UserController(UserService userService, DemoAutowire autowire) {
        this.userService = userService;
        this.autowire = autowire;
    }

    @GetMapping("/demo")
    public String test() {
        System.out.println(autowire.getMyValue());
        return "";
    }


    // Requete HTTP de type GET
    @GetMapping("/users")
    public String displayUsers(Model model) {
        // Traitement métier
        List<User> userList = userService.fetchUsers();
        // Put data in view
        model.addAttribute("users", userList);
        // HTML FILE returned
        return "user-list";
    }

}
