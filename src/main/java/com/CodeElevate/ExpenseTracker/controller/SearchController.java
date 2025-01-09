package com.CodeElevate.ExpenseTracker.controller;

import com.CodeElevate.ExpenseTracker.dto.SearchDTO;
import com.CodeElevate.ExpenseTracker.services.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping
    public List<Map<String, Object>> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String type, // Income hoặc Expense
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        // Tạo SearchDTO từ các query parameters
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setTitle(title);
        searchDTO.setCategory(category);
        searchDTO.setType(type);
        searchDTO.setStartDate(startDate);
        searchDTO.setEndDate(endDate);

        // Gọi service để xử lý
        return searchService.search(searchDTO);
    }
}