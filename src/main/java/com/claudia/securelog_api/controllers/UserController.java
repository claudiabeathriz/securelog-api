package com.claudia.securelog_api.controllers;

import com.claudia.securelog_api.dto.UserRequestDTO;
import com.claudia.securelog_api.dto.UserResponseDTO;
import com.claudia.securelog_api.entities.User;
import com.claudia.securelog_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
// classe recebe requisições HTTP e retorna JSON
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO create(@RequestBody @Valid UserRequestDTO dto) {
        // RB pega JSON do Postman e transforma em objeto Java
        User user = new User(
                null,
                dto.getName(),
                dto.getEmail(),
                dto.getPassword()
        );

        User savedUser = userService.create(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
                // retorna transformando em DTO (ou seja, sem senha)
        );
    }

    @GetMapping
    public List<UserResponseDTO> findAll() {
        return userService.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        // converte cada user em DTO
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}