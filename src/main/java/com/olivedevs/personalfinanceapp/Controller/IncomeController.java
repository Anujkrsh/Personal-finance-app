package com.olivedevs.personalfinanceapp.Controller;

import com.olivedevs.personalfinanceapp.dto.SaveIncomeDTO;
import com.olivedevs.personalfinanceapp.model.Income;
import com.olivedevs.personalfinanceapp.service.IncomeService;
import com.olivedevs.personalfinanceapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/income")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/add")
    public ResponseEntity<?> addIncome(@RequestBody SaveIncomeDTO income, @RequestHeader("Authorization") String authToken){
        if(income == null|| income.getAmount()==null|| income.getDate()==null){
                return new ResponseEntity<>("Please provide valid input", HttpStatusCode.valueOf(400));
        }
        try {
            Long uuid = jwtUtil.extractUserId(authToken.substring(7));
            Income incomeToSave = Income.builder().userId(uuid).description(income.getDescription()).source(income.getSource())
                    .amount(income.getAmount()).build();
            incomeService.save(incomeToSave);
            return ResponseEntity.ok(income);
        }catch (Exception ex){
            return new ResponseEntity<>("Something Went Wrong "+ex.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteIncome(@RequestParam Long id, @RequestHeader("Authorization") String authToken){
        if(id==0){
            return new ResponseEntity<>("Please provide valid input", HttpStatusCode.valueOf(400));
        }
        try {
            Long uuid = jwtUtil.extractUserId(authToken.substring(7));
            incomeService.delete(id);
            return ResponseEntity.ok("Deleted Income Successfully");
        }catch (Exception ex){
            return new ResponseEntity<>("Something Went Wrong "+ex.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getIncome( @RequestHeader("Authorization") String authToken){
        try {
            Long uuid = jwtUtil.extractUserId(authToken.substring(7));
            Optional<Income> income =incomeService.findById(uuid);
            return ResponseEntity.ok(income);
        }catch (Exception ex){
            return new ResponseEntity<>("Something Went Wrong "+ex.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateIncome(long incomeId, @RequestHeader("Authorization") String authToken){
        try {
            Long uuid = jwtUtil.extractUserId(authToken.substring(7));
            Optional<Income> income =incomeService.findById(uuid);
            Income incomeToUpdate = income.stream().filter(t->t.getId()==incomeId).findFirst().orElse(null);
            if(incomeToUpdate==null){
                return new ResponseEntity<>("Income id might be wrong"+incomeId, HttpStatusCode.valueOf(400));
            }
            return ResponseEntity.ok(income);
        }catch (Exception ex){
            return new ResponseEntity<>("Something Went Wrong "+ex.getMessage(), HttpStatusCode.valueOf(400));
        }
    }





}
