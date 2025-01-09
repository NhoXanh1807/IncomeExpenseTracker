package com.CodeElevate.ExpenseTracker.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private Long user_id;
    private String username;
    private String email;
    private String password;

}