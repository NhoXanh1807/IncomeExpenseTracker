package com.CodeElevate.ExpenseTracker.services.income;

import com.CodeElevate.ExpenseTracker.dto.IncomeDTO;
import com.CodeElevate.ExpenseTracker.entity.Income;

import java.util.List;

import org.springframework.data.domain.Page;

public interface IncomeService {
    Income postIncome(IncomeDTO incomeDTO);

    Page<IncomeDTO> getAllIncomes(Integer page, String sortType);

    Income updateIncome(Long id, IncomeDTO incomeDTO);

    IncomeDTO getIncomeById(Long id);

    void deleteIncome(Long id);
}
