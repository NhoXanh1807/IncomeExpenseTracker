package com.CodeElevate.ExpenseTracker.services.user;

import com.CodeElevate.ExpenseTracker.dto.UserDTO;
import com.CodeElevate.ExpenseTracker.entity.User;
import com.CodeElevate.ExpenseTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Dùng để mã hóa mật khẩu

    @Override
    public UserDTO updateProfile(Long userId, UserDTO userDTO) {
        // Lấy user từ database
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Cập nhật các thông tin hồ sơ (không bao gồm ảnh và mật khẩu)
            user.setFname(userDTO.getFname());
            user.setLname(userDTO.getLname());
            user.setDob(userDTO.getDob());
            user.setAddr(userDTO.getAddr());

            // Lưu lại thông tin cập nhật vào database
            userRepository.save(user);

            return user.getFullUserDto(); // Trả về thông tin đầy đủ
        }
        return null;
    }

    @Override
    public boolean changePassword(Long userId, String oldPassword, String newPassword) {
        // Lấy user từ database
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Kiểm tra mật khẩu cũ
            if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                throw new IllegalArgumentException("Old password is incorrect.");
            }

            // Kiểm tra mật khẩu mới
            if (newPassword == null || newPassword.isBlank()) {
                throw new IllegalArgumentException("New password cannot be empty.");
            }

            if (passwordEncoder.matches(newPassword, user.getPassword())) {
                throw new IllegalArgumentException("New password cannot be the same as the old password.");
            }

            // Cập nhật mật khẩu mới
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    @Override
    public UserDTO changeProfileImage(Long userId, String newImageUrl) {
        // Lấy user từ database
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Kiểm tra URL mới
            if (newImageUrl == null || newImageUrl.isBlank()) {
                throw new IllegalArgumentException("Image URL cannot be empty.");
            }

            // Cập nhật ảnh đại diện
            user.setImg(newImageUrl);

            // Lưu lại thông tin cập nhật vào database
            userRepository.save(user);

            return user.getFullUserDto(); // Trả về thông tin đầy đủ
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }
}