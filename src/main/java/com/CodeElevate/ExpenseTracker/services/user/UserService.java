package com.CodeElevate.ExpenseTracker.services.user;

import com.CodeElevate.ExpenseTracker.dto.UserDTO;
import com.CodeElevate.ExpenseTracker.entity.User;

public interface UserService {
    // Cập nhật thông tin hồ sơ user (không bao gồm ảnh và password)
    UserDTO updateProfile(Long userId, UserDTO userDTO);

    // Thay đổi mật khẩu (cần phải nhập lại mật khẩu cũ)
    boolean changePassword(Long userId, String oldPassword, String newPassword);

    // Thay đổi hình đại diện (chỉ thay đổi ảnh)
    UserDTO changeProfileImage(Long userId, String newImageUrl);
}
