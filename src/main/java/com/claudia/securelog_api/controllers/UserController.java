package com.claudia.securelog_api.controllers;

import com.claudia.securelog_api.dtos.UserResponseDTO;
import com.claudia.securelog_api.entities.User;
import com.claudia.securelog_api.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
// classe recebe requisições HTTP e retorna JSON
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    // traz a logica da aplicação
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // injeção de dependencia

    @PostMapping
    public UserResponseDTO create(@RequestBody User user) {
        // RB pega JSON do Postman e transforma em objeto Java
        User savedUser = userService.create(user);
        // chama lógica do service
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
                // busca todos os users
                .stream()
                // transforma lista em stream
                .map(user -> new UserResponseDTO(
                        // converte cada user em DTO
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
                // volta pra lista
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable Long id) {
        // pega o ID da URL
        User user = userService.findById(id);
        // busca no banco (service pega Optional<User> user = userRepository.findById(id);)
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
                // retorna sem senha
        );
    }
}