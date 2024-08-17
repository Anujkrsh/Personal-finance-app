package com.olivedevs.personalfinanceapp.Controller;

import com.olivedevs.personalfinanceapp.dto.BudgetDTO;
import com.olivedevs.personalfinanceapp.model.Budget;
import com.olivedevs.personalfinanceapp.repository.BudgetRepository;
import com.olivedevs.personalfinanceapp.service.BudgetService;
import com.olivedevs.personalfinanceapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BudgetRepository budgetRepository;


    @PostMapping("/create")
    public ResponseEntity<?>  createBudget(@RequestBody BudgetDTO budgetDTO, @RequestHeader("Authorization") String authToken) {
        if((budgetDTO.getAmount() == 0) || (budgetDTO.getEnd_date() == null) || (budgetDTO.getStart_date() == null)) {
            return ResponseEntity.badRequest().body("Budget is not correct");
        }
        try {
            Long uuid = jwtUtil.extractUserId(authToken.substring(7));
            Budget budget = Budget.builder().userId(uuid).category(budgetDTO.getCategory()).
                    start_date(budgetDTO.getStart_date()).end_date(budgetDTO.getEnd_date()).build();
            budgetService.save(budget);
            return new ResponseEntity<>("Budget Saved for the User", HttpStatusCode.valueOf(201));
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?>  createBudget(@RequestHeader("Authorization") String authToken) {
        try {
            Long uuid = jwtUtil.extractUserId(authToken.substring(7));
            List<Budget> budgets= budgetRepository.findBudgetById(uuid);
            return new ResponseEntity<>(budgets, HttpStatusCode.valueOf(200));
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
