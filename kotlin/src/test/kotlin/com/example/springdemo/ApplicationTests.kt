package com.example.springdemo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ApplicationTests {

    @Test
    fun contextLoads() {
    }
    
    @Test
    fun canEncodePassword() {
        val encoder = Sha256PasswordEncoder()
        val encoded = encoder.encode("1a2b3c\$D")
        assert(encoded == "VHx1f1pM2bV/FV2bbuWvnbIcdumfLWtM7oeWPBKR6vM=")
        println(encoded);
    }

}
