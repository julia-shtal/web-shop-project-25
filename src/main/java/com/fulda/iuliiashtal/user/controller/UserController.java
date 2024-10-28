package com.fulda.iuliiashtal.user.controller;

import com.fulda.iuliiashtal.user.entity.User;
import com.fulda.iuliiashtal.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("api/users")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("api/users/{id}")
    public User getUserDetail(@PathVariable Long id) {
        return userService.getUserById(id).orElse(null);
    }

    @GetMapping("api/users/name")
    public List<User> getUsersByColor(@RequestParam String firstName, @RequestParam String lastName) {
        return userService.getUsersByFullName(firstName, lastName);
    }

    @PostMapping("api/users")
    public User addUser(@RequestParam String firstName,
                        @RequestParam String lastName,
                        @RequestParam String email) {
        return userService.create(firstName, lastName, email);
    }
}
