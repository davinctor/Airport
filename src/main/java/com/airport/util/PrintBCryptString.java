package com.airport.util;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PrintBCryptString {
    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder(12);
        System.out.println(encoder.matches("type here some string", encoder.encode("type here some string")));
        System.out.println(encoder.encode("type here some string"));
    }
}