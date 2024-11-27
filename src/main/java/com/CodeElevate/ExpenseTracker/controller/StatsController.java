package com.CodeElevate.ExpenseTracker.controller;

import com.CodeElevate.ExpenseTracker.dto.GraphDTO;
import com.CodeElevate.ExpenseTracker.stats.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StatsController {
    private final StatsService statsService;
    public ResponseEntity<GraphDTO> getChartDetails(){
        return ResponseEntity.ok(statsService.getChartData());
    }
    @GetMapping("/chart")
    public ResponseEntity<GraphDTO> getChartData(){return ResponseEntity.ok(statsService.getChartData());}
    @GetMapping
    public ResponseEntity<?> getStats(){
        return ResponseEntity.ok(statsService.getStats());
    }
}
