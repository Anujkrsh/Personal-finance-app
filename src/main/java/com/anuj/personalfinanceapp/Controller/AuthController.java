package com.anuj.personalfinanceapp.Controller;


import java.util.Optional;

import com.anuj.personalfinanceapp.dto.AuthRequest;
import com.anuj.personalfinanceapp.dto.AuthResponse;
import com.anuj.personalfinanceapp.model.User;
import com.anuj.personalfinanceapp.service.UserService;
import com.anuj.personalfinanceapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        Optional<User> existingUser = userService.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        String tempPass= passwordEncoder.encode(user.getPassword());
        user.setPassword(tempPass);
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws AuthenticationException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );


            // Get user details
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.findByUsername(userDetails.getUsername()).get();
            // Generate JWT token
            String jwt = jwtUtil.generateToken(userDetails.getUsername(), user.getId());

            // Return JWT token in response
            return ResponseEntity.ok(new AuthResponse(jwt));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        // Logic for logout
        return ResponseEntity.ok("Logout successful");
    }
}
