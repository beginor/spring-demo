package com.example.springdemo

import org.junit.jupiter.api.Test

class KotlinTests {

    @Test
    fun _01_canTest() {
        val upperCase1: (String) -> String = { str: String -> str.toUpperCase() }
        val upperCase2: (String) -> String = { str -> str.toUpperCase() }
        val upperCase3 = { str: String -> str.toUpperCase() }
        val upperCase5: (String) -> String = { it.toUpperCase() };
        val upperCase6: (String) -> String = String::toUpperCase;
        
        assert(upperCase1("hello") == "HELLO") { "error upper case 1" }
        println(upperCase1("hello"))
        println(upperCase2("hello"))
        println(upperCase3("hello"))
        println(upperCase5("hello"))
        println(upperCase6("hello"))
    }
    
    @Test
    fun _02_canGroupBy() {
        data class Person(val name: String, val city: String, val phone: String);
        val people = listOf(
            Person("John", "Boston", "+1-888-123456"),
            Person("Sarah", "Munich", "+49-777-789123"),
            Person("Svyatoslav", "Saint-Petersburg", "+7-999-456789"),
            Person("Vasilisa", "Saint-Petersburg", "+7-999-123456")
        );
        val phoneBook = people.associateBy { it.phone }
        val cityBook = people.associateBy({it.phone}, {it.city});
    }
}
