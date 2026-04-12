package com.claudia.securelog_api.services;

import com.claudia.securelog_api.entities.User;
import com.claudia.securelog_api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// annotation para informar que é um componente de negócio
public class UserService {

    private final UserRepository userRepository;
    // dependência do repo; não muda

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // injeção de dependência

    public User create(User user) {
        return userRepository.save(user);
    }
    // no sql => INSERT INTO users (...)

    public List<User> findAll() {
        return userRepository.findAll();
    }
    // no sql => SELECT * FROM users;

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        // optional porque o user pode existir ou nao
        return user.orElseThrow(() -> new RuntimeException("User not found"));
        // tratamento de exceção evita que retorne null = boa prática
    }
    // no sql => SELECT * FROM users WHERE id = ?
    // ou
    // SELECT u.id, u.name, u.email, u.password
    //FROM users u
    //WHERE u.id = ?;
}