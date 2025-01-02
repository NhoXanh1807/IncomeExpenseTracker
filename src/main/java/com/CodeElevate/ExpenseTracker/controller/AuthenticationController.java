package com.CodeElevate.ExpenseTracker.controller;

import com.CodeElevate.ExpenseTracker.dto.UserDTO;
import com.CodeElevate.ExpenseTracker.services.authentication.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestParam String username,
                                            @RequestParam String email,
                                            @RequestParam int age,
                                            @RequestParam String password) {
        UserDTO userDTO = authenticationService.register(username, email, age, password);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username,
                                        @RequestParam String password) {
        String response = authenticationService.login(username, password);
        return ResponseEntity.ok(response);
    } 
}
