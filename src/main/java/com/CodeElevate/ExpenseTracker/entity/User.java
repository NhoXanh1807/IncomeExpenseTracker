package com.CodeElevate.ExpenseTracker.entity;

import com.CodeElevate.ExpenseTracker.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private int age;
    private String password;

    public UserDTO getUserDto() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setAge(age);
        return userDTO;
    }
}
