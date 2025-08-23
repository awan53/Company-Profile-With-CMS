package com.alkes.alkse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

        @GetMapping("/login")
    public String loginPage() {
        return "login"; //  Akan merender src/main/resources/templates/login.html
    }

    @GetMapping("/admin/index")
    public String homePage() {
        return "admin/index"; // Akan merender src/main/resources/templates/admin/index.html
    }

    @PostMapping("/logout")
    public String logoutPage() {
        return "redirect:/login?logout";
    }
}
