package com.CodeElevate.ExpenseTracker.repository;

import com.CodeElevate.ExpenseTracker.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
        List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate);

        @Query("SELECT SUM(i.amount) FROM Income i")
        Double sumAllAmount();

        Optional<Income> findFirstByOrderByDateDesc();

        @Query("SELECT i FROM Income i WHERE " +
                        "(:title IS NULL OR i.title LIKE %:title%) AND " +
                        "(:category IS NULL OR i.category = :category) AND " +
                        "(:startDate IS NULL OR i.date >= :startDate) AND " +
                        "(:endDate IS NULL OR i.date <= :endDate)")
        List<Income> findBySearchParams(
                        @Param("title") String title,
                        @Param("category") String category,
                        @Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);
}