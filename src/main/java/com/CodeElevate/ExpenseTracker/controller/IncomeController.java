package com.CodeElevate.ExpenseTracker.controller;

import com.CodeElevate.ExpenseTracker.dto.IncomeDTO;

import com.CodeElevate.ExpenseTracker.entity.Income;
import com.CodeElevate.ExpenseTracker.services.income.IncomeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/income")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IncomeController {
    private final IncomeService incomeService;

    @PostMapping
    public ResponseEntity<?> postIncome(@RequestBody IncomeDTO incomeDTO) {
        try {
            Income createdIncome = incomeService.postIncome(incomeDTO);
            if (createdIncome != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(createdIncome);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            // Log lỗi để dễ dàng debug
            e.printStackTrace(); // Hoặc dùng logger để ghi lại lỗi
            // Phản hồi với mã lỗi 500 và thông báo lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllIncomes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "asc") String sort) {
        return ResponseEntity.ok(incomeService.getAllIncomes(page, sort));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIncome(@PathVariable Long id, @RequestBody IncomeDTO incomeDTO) {
        try {
            return ResponseEntity.ok(incomeService.updateIncome(id, incomeDTO));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIncomeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(incomeService.getIncomeById(id));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable Long id) {
        try {
            incomeService.deleteIncome(id);
            return ResponseEntity.ok(null);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
}