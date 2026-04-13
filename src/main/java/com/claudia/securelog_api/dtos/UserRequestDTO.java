package com.claudia.securelog_api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;

    // como colocar ainda mais restrições de senha? tipos de caracteres etc

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}