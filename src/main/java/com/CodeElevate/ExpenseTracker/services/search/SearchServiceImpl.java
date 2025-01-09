package com.CodeElevate.ExpenseTracker.services.search;

import com.CodeElevate.ExpenseTracker.dto.SearchDTO;
import com.CodeElevate.ExpenseTracker.entity.Expense;
import com.CodeElevate.ExpenseTracker.entity.Income;
import com.CodeElevate.ExpenseTracker.repository.ExpenseRepository;
import com.CodeElevate.ExpenseTracker.repository.IncomeRepository;
import com.CodeElevate.ExpenseTracker.services.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public List<Map<String, Object>> search(SearchDTO searchDTO) {
        List<Map<String, Object>> results = new ArrayList<>();

        // Tìm kiếm trong bảng Income nếu type là Income hoặc không chỉ định type
        if ("Income".equalsIgnoreCase(searchDTO.getType()) || searchDTO.getType() == null) {
            List<Income> incomes = incomeRepository.findBySearchParams(
                    searchDTO.getTitle(),
                    searchDTO.getCategory(),
                    searchDTO.getStartDate(),
                    searchDTO.getEndDate());
            for (Income income : incomes) {
                Map<String, Object> result = new HashMap<>();
                result.put("type", "Income");
                result.put("id", income.getId());
                result.put("title", income.getTitle());
                result.put("amount", income.getAmount());
                result.put("date", income.getDate());
                result.put("category", income.getCategory());
                result.put("description", income.getDescription());
                results.add(result);
            }
        }

        // Tìm kiếm trong bảng Expense nếu type là Expense hoặc không chỉ định type
        if ("Expense".equalsIgnoreCase(searchDTO.getType()) || searchDTO.getType() == null) {
            List<Expense> expenses = expenseRepository.findBySearchParams(
                    searchDTO.getTitle(),
                    searchDTO.getCategory(),
                    searchDTO.getStartDate(),
                    searchDTO.getEndDate());
            for (Expense expense : expenses) {
                Map<String, Object> result = new HashMap<>();
                result.put("type", "Expense");
                result.put("id", expense.getId());
                result.put("title", expense.getTitle());
                result.put("amount", expense.getAmount());
                result.put("date", expense.getDate());
                result.put("category", expense.getCategory());
                result.put("description", expense.getDescription());
                results.add(result);
            }
        }

        return results;
    }
}
