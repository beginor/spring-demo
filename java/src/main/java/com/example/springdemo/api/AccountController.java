package com.example.springdemo.api;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.sql.DataSource;
import java.security.Principal;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final DataSource dataSource;

    public AccountController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/info")
    public String getInfo(Authentication authentication) {
        SecurityExpressionRoot root;
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return "Hello, " + authentication.getName();
    }

}
