package com.CodeElevate.ExpenseTracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

import com.CodeElevate.ExpenseTracker.dto.ExpenseDTO;

@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String category;

    private LocalDate date;

    private Integer amount;

    public ExpenseDTO getExpenseDto() {
        ExpenseDTO expenseDTO = new ExpenseDTO();

        expenseDTO.setId(id);
        expenseDTO.setTitle(title);
        expenseDTO.setAmount(amount);
        expenseDTO.setDate(date);
        expenseDTO.setCategory(category);
        expenseDTO.setDescription(description);

        return expenseDTO;
    }
}
