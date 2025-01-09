package com.CodeElevate.ExpenseTracker.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class SearchDTO {
    private String title; // Tiêu đề tìm kiếm (Income hoặc Expense)
    private String category; // Loại (category) của Income/Expense
    private String Type; // Income/Expense
    private LocalDate startDate; // Ngày bắt đầu tìm kiếm
    private LocalDate endDate; // Ngày kết thúc tìm kiếm
}
