package com.cdek.controller;

import com.cdek.domain.User;
import com.cdek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUser(@RequestParam(name = "username", defaultValue = "") String username, Model model) {

        List<User> users = null;

        if (username.equals("")) {
            users = userService.getAllUsers();
        } else {
            User user = userService.getUser(username);

            if (user != null) {
                users = Collections.singletonList(user);
            }
        }

        if (users != null && users.size() > 0) {
            model.addAttribute("users", users);
        }

        model.addAttribute("username", username);

        return "user";
    }

    @GetMapping("/new_user")
    public String createUserPage() {
        return "new_user";
    }

    @PostMapping("/new_user")
    public String addUser(@RequestParam("name") String username, Model model) {

        User user = new User(username);

        if (!userService.addUser(user)) {
            model.addAttribute("message", "User exists!");
        } else {
            model.addAttribute("message", String.format("User with name: <b> %s </b> added.", user.getName()));
        }

        return "new_user";
    }

}
