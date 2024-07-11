package com.olivedevs.personalfinanceapp.Controller;

import com.olivedevs.personalfinanceapp.dto.ExpenseRequestDto;
import com.olivedevs.personalfinanceapp.model.Expense;
import com.olivedevs.personalfinanceapp.repository.ExpenseRepository;
import com.olivedevs.personalfinanceapp.service.ExpenseService;
import com.olivedevs.personalfinanceapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ExpenseRepository expenseRepository;


    @PostMapping("/Create")
    public ResponseEntity<?> createExpense(@RequestBody ExpenseRequestDto expenseRequest, @RequestHeader("Authorization") String authToken) {
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
                    .item(expenseRequest.getItem()).build();
            expenseService.save(expense);
            return new ResponseEntity<>("Expense Saved for the User", HttpStatusCode.valueOf(201));
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/Fetch")
    public ResponseEntity<?> fetchExpense(@RequestHeader("Authorization") String authToken) {
        try {
            long uuid = jwtUtil.extractUserId(authToken.substring(7));
            List<Expense> expense=expenseRepository.findByUserId(uuid);
            List<Expense> sortedExpenses = expense.stream()
                    .sorted((e1, e2) -> e1.getId().compareTo(e2.getId()))
                    .toList();
            if(!sortedExpenses.isEmpty()) {
                return ResponseEntity.ok(sortedExpenses);
            }else{
                return ResponseEntity.badRequest().body("Expense Not Found");
            }
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editExpense/{id}")
    public ResponseEntity<?> editExpense(@RequestHeader("Authorization") String authToken,@PathVariable Long  id, @RequestBody ExpenseRequestDto expenseRequestDto) {
        try{
            validateExpense(expenseRequestDto);
            long uuid = jwtUtil.extractUserId(authToken.substring(7));
            List<Expense> expense=expenseRepository.findByUserId(uuid);
            Expense expenseToEdit = expense.stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formattedDateTime = now.format(formatter);
            if(expenseToEdit==null) {
                return ResponseEntity.badRequest().body("Expense Not Found");
            }else{
                expenseToEdit= Expense.builder().amount(expenseRequestDto.getAmount()).date(formattedDateTime).
                        item(expenseRequestDto.getItem())
                        .description(expenseRequestDto.getDescription()).build();
                expenseRepository.save(expenseToEdit);
                return ResponseEntity.ok(expenseToEdit);
            }
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteExpense/{id}")
    public ResponseEntity<?> deleteExpense(@RequestHeader("Authorization") String authToken,@PathVariable Long id) {
        try{
            expenseRepository.deleteById(id);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return new ResponseEntity<>("Expense Deleted",HttpStatusCode.valueOf(200));
    }

    private void validateExpense(ExpenseRequestDto expense) {
        if (expense==null||expense.getDescription()==null) {
            throw new ResponseStatusException((HttpStatusCode.valueOf(401)),"Expense in Request Not Found");
        }
    }


}
