package com.anuj.personalfinanceapp.Controller;

import com.anuj.personalfinanceapp.dto.ExpenseRequestDto;
import com.anuj.personalfinanceapp.model.Expense;
import com.anuj.personalfinanceapp.service.ExpenseService;
import com.anuj.personalfinanceapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/Create")
    private ResponseEntity<?> createExpense(@RequestBody ExpenseRequestDto expenseRequest, @RequestHeader("Authorization") String authToken) {
        if(expenseRequest.getAmount() == null) {
            return ResponseEntity.badRequest().body("amount & item are required");
        }
        try {
            Long uuid = jwtUtil.extractUserId(authToken.substring(7));
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formattedDateTime = now.format(formatter);
            String description = expenseRequest.getDescription() == null ? "No Description available" : expenseRequest.getDescription();
            Expense expense = Expense.builder().userId(uuid).
                    amount(expenseRequest.getAmount()).description(description)
                    .date(formattedDateTime)
                    .build();
            expenseService.save(expense);
            return new ResponseEntity<>("Expense Saved for the User", HttpStatusCode.valueOf(201));
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
