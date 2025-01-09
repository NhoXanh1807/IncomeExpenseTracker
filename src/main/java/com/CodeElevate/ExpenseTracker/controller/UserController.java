package com.CodeElevate.ExpenseTracker.controller;

import com.CodeElevate.ExpenseTracker.dto.UserDTO;
import com.CodeElevate.ExpenseTracker.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Cập nhật thông tin hồ sơ user (không bao gồm ảnh và mật khẩu)
    @PutMapping("/{userId}/profile")
    public UserDTO updateProfile(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        return userService.updateProfile(userId, userDTO);
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<?> changePassword(
            @PathVariable Long userId,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        try {
            boolean result = userService.changePassword(userId, oldPassword, newPassword);
            if (result) {
                return ResponseEntity.ok("Password updated successfully.");
            } else {
                return ResponseEntity.badRequest().body("Old password is incorrect or user does not exist.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/{userId}/profile-image")
    public ResponseEntity<?> changeProfileImage(
            @PathVariable Long userId,
            @RequestParam String newImageUrl) {
        try {
            UserDTO updatedUser = userService.changeProfileImage(userId, newImageUrl);
            if (updatedUser != null) {
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.badRequest().body("User not found or invalid request.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }
}