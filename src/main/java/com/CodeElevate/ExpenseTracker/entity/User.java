package com.CodeElevate.ExpenseTracker.entity;

import com.CodeElevate.ExpenseTracker.dto.UserDTO;
import com.CodeElevate.ExpenseTracker.dto.UserRegisterDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String username;
    private String email;
    private String password;

    private LocalDate dob; // Ngày sinh
    private String fname; // Tên
    private String lname; // Họ
    private String addr; // Địa chỉ
    private String img; // Đường dẫn ảnh đại diện

    // Phương thức lấy UserRegisterDTO cho chức năng đăng ký
    public UserRegisterDTO getUserDto() {
        UserRegisterDTO userDTO = new UserRegisterDTO();
        userDTO.setUser_id(user_id);
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        return userDTO;
    }

    // Phương thức lấy UserDTO đầy đủ cho các chức năng như cập nhật hồ sơ
    public UserDTO getFullUserDto() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(user_id);
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setDob(dob);
        userDTO.setFname(fname);
        userDTO.setLname(lname);
        userDTO.setAddr(addr);
        userDTO.setImg(img);
        return userDTO;
    }
}
