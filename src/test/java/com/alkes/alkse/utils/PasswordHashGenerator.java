package com.alkes.alkse.utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {
    public static void main (String[] args) {
        // Generate a hashed password using BCrypt
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123"; // Replace with your actual password
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }

}
