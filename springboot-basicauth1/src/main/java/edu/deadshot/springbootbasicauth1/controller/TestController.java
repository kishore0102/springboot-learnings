package edu.deadshot.springbootbasicauth1.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.deadshot.springbootbasicauth1.security.CustomUserDetailsService;
import edu.deadshot.springbootbasicauth1.security.JwtUtility;

@RestController
public class TestController {

    @Autowired
    JwtUtility jwtUtility;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello! You're most welcomed to springboot auth based on JWT";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> request) throws Exception {
        String username = request.get("username").toString();
        String password = request.get("password").toString();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException ex){
            throw new Exception("Invalid credentials", ex);
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        String jwt = jwtUtility.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }
    
}
