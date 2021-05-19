package com.example.springdemo.api;

import com.example.springdemo.models.LoginModel;
import com.example.springdemo.security.JwtTokenUtil;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AuthenticationManager authManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AccountController(
        AuthenticationManager authManager,
        JwtTokenUtil jwtTokenUtil
        ) {
        this.authManager = authManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("info")
    public String getInfo(Authentication authentication) {
        SecurityExpressionRoot root;
        var user = (UserDetails) authentication.getPrincipal();
        return "Hello, " + authentication.getName();
    }
    
    @PostMapping("login")
    public ResponseEntity login(@RequestBody LoginModel model) {
        try {
            var token = new UsernamePasswordAuthenticationToken(
                model.getUsername(),
                model.getPassword()
            );
            var auth = authManager.authenticate(token);
            var user = (UserDetails) auth.getPrincipal();
            var claims = new JWTClaimsSet.Builder()
                .claim("username", user.getUsername())
                .issuer("/spring-demo")
                .expirationTime(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .build();
            var jwt = jwtTokenUtil.generateToken(claims);
            return ResponseEntity.ok(jwt);
        }
        catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
