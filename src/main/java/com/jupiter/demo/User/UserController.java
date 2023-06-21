package com.jupiter.demo.User;

import com.jupiter.demo.Auth.AuthService;
import com.jupiter.demo.Payload.LoginRequest;
import com.jupiter.demo.Payload.RegisterRequest;
import com.jupiter.demo.Payload.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/jupiter")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            User user = this.userService.registerUser(registerRequest.getUsername(), registerRequest.getPassword(), registerRequest.getEmail());
            ResponseBody<User> responseBody = new ResponseBody<>(user,
                    "User registered successfully", null);
            return ResponseEntity.ok(responseBody);
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            String userId = this.userService.verifyUserAndReturnUserId(loginRequest.getEmail(), loginRequest.getPassword());
            String token = this.authService.generateToken(userId);
            ResponseBody<String> responseBody = new ResponseBody<>(token,
                    "User logged in successfully", null);
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
