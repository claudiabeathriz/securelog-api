package com.claudia.securelog_api.dto;

public class UserResponseDTO {
    // resposta da API

    private Long id;
    private String name;
    private String email;
    // campos que serão mostrados; SEM SENHA

    public UserResponseDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
