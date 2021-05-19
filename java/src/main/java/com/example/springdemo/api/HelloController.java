package com.example.springdemo.api;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/hello")
public class HelloController {

    private JdbcTemplate jdbcTemplate;

    public HelloController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("user")
    @PreAuthorize("hasAuthority('hello.user')")
    public String helloUser() {
        return "Hello, user!";
    }

    @GetMapping("admin")
    @PreAuthorize("hasAuthority('hello.admin')")
    public String helloAdmin() {
        return "Hello, admin";
    }

}
