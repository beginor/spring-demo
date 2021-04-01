package com.example.springdemo.api

import com.example.springdemo.models.LoginModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.sql.DataSource

@RestController
@RequestMapping("api/account")
class AccountController(
    private val dataSource: DataSource
) {
    
    @GetMapping("info")
    fun getInfo(auth: Authentication): ResponseEntity<String> {
        if (auth.isAuthenticated) {
            return ResponseEntity.ok(auth.name);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED")
    }
    
    fun test(): ResponseEntity<LoginModel> {
        return ResponseEntity.noContent().build();
    }

}
