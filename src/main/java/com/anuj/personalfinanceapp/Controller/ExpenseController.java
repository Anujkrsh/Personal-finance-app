package com.anuj.personalfinanceapp.Controller;

import com.anuj.personalfinanceapp.model.User;
import com.anuj.personalfinanceapp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;


/*//    @GetMapping("")
public ResponseEntity<?> register(@RequestBody User user) {
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
