package com.claudia.securelog_api.controllers;

import com.claudia.securelog_api.dto.LoginRequestDTO;
import com.claudia.securelog_api.entities.User;
import com.claudia.securelog_api.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO dto) {

        User user = userService.login(dto.getEmail(), dto.getPassword());

        return "Login successful for user: " + user.getEmail();
    }
}