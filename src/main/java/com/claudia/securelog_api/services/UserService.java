package com.claudia.securelog_api.services;

import com.claudia.securelog_api.entities.User;
import com.claudia.securelog_api.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// annotation para informar que é um componente de negócio
public class UserService {

    private final UserRepository userRepository;
    // dependência do repo; não muda

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    // injeção de dependência + senha criptografada

    public User create(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
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

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        // optional porque o user pode existir ou nao
        return user.orElseThrow(() -> new RuntimeException("User not found"));
        // tratamento de exceção evita que retorne null = boa prática
    }

    public User login(String email, String password) {
        User user = findByEmail(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }
}