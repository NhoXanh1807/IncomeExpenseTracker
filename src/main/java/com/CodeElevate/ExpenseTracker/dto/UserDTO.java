package com.CodeElevate.ExpenseTracker.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDTO {
    private Long user_id;
    private String username;
    private String email;
    private String password;
    private LocalDate dob;
    private String fname;
    private String lname;
    private String addr;
    private String img;
}
