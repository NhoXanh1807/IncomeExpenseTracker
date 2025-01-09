package com.CodeElevate.ExpenseTracker.repository;

import com.CodeElevate.ExpenseTracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
        List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

        @Query("SELECT SUM(e.amount) FROM Expense e")
        Double sumAllAmount();

        Optional<Expense> findFirstByOrderByDateDesc();

        @Query("SELECT e FROM Expense e WHERE " +
                        "(:title IS NULL OR e.title LIKE %:title%) AND " +
                        "(:category IS NULL OR e.category = :category) AND " +
                        "(:startDate IS NULL OR e.date >= :startDate) AND " +
                        "(:endDate IS NULL OR e.date <= :endDate)")
        List<Expense> findBySearchParams(
                        @Param("title") String title,
                        @Param("category") String category,
                        @Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

}
