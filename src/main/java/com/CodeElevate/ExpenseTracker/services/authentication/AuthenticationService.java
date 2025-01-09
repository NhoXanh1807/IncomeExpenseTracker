package com.CodeElevate.ExpenseTracker.services.authentication;

import com.CodeElevate.ExpenseTracker.dto.UserRegisterDTO;

public interface AuthenticationService {
    UserRegisterDTO register(String username, String email, String password);

    String login(String username, String password);
}