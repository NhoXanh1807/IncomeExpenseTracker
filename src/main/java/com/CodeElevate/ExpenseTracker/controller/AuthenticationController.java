package com.CodeElevate.ExpenseTracker.controller;

import com.CodeElevate.ExpenseTracker.dto.UserRegisterDTO;
import com.CodeElevate.ExpenseTracker.dto.UserLoginDTO;
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
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO userDTO) {
        try {
            // Gọi service để xử lý logic đăng ký
            UserRegisterDTO response = authenticationService.register(
                    userDTO.getUsername(),
                    userDTO.getEmail(),
                    userDTO.getPassword());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            // Xử lý các lỗi liên quan đến logic như username/email đã tồn tại
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        } catch (Exception ex) {
            // Xử lý các lỗi không mong muốn khác
            ex.printStackTrace(); // In stack trace ra log để dễ debug
            return ResponseEntity.status(500).body("Internal Server Error: " + ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        String response = authenticationService.login(username, password);
        return ResponseEntity.ok(response);
    }
}