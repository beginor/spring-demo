package com.example.springdemo.models

data class LoginModel(
    val username: String,
    val password: String,
    val rememberMe: Boolean
)
