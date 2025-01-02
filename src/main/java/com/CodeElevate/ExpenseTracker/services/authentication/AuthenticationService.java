package com.CodeElevate.ExpenseTracker.services.authentication;

import com.CodeElevate.ExpenseTracker.dto.UserDTO;

public interface AuthenticationService {
    UserDTO register(String username, String email, int age, String password);
    String login(String username, String password);
}
