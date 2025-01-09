package com.CodeElevate.ExpenseTracker.services.authentication;

import com.CodeElevate.ExpenseTracker.dto.UserRegisterDTO;
import com.CodeElevate.ExpenseTracker.entity.User;
import com.CodeElevate.ExpenseTracker.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserRegisterDTO register(String username, String email, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists!");
        }

        String hashedPassword = passwordEncoder.encode(password);
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);

        userRepository.save(newUser);
        return newUser.getUserDto();
    }

    @Override
    public String login(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty() ||
                !passwordEncoder.matches(password, userOptional.get().getPassword())) {
            throw new IllegalArgumentException("Invalid username or password!");
        }
        return "Login successful!";
    }
}