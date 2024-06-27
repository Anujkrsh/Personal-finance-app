package com.anuj.personalfinanceapp.Controller;

import com.anuj.personalfinanceapp.model.Expense;
import com.anuj.personalfinanceapp.model.User;
import com.anuj.personalfinanceapp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;


    @PostMapping
    private ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {


    }


/*    @GetMapping("get-expense")
    public ResponseEntity<?> GetExpense(@RequestBody Expens user) {
    Optional<User> existingUser = userService.findByUsername(user.getUsername());
    if (existingUser.isPresent()) {
        return ResponseEntity.badRequest().body("Username is already taken");
    }
    String tempPass= passwordEncoder.encode(user.getPassword());
    user.setPassword(tempPass);
    userService.saveUser(user);
    return ResponseEntity.ok("User registered successfully");
}*/
}
