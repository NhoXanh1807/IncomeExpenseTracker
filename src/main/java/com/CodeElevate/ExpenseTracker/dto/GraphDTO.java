package com.CodeElevate.ExpenseTracker.dto;

import com.CodeElevate.ExpenseTracker.entity.Expense;
import com.CodeElevate.ExpenseTracker.entity.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDTO {
    private List<Expense> expenseList;
    private List<Income> incomeList;
}
