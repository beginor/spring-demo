package com.example.springdemo

import org.springframework.security.crypto.password.PasswordEncoder
import java.lang.Exception
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*

class Sha256PasswordEncoder : PasswordEncoder {

    override fun encode(rawPassword: CharSequence): String {
        try {
            val digest = MessageDigest.getInstance("SHA-256");
            val buff = rawPassword.toString().toByteArray(StandardCharsets.UTF_8);
            val hash = digest.digest(buff);
            return Base64.getEncoder().encodeToString(hash);
        } catch (ex: Exception) {
            return "";
        }
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        val encoded = encode(rawPassword);
        return encoded == encodedPassword;
    }

}
