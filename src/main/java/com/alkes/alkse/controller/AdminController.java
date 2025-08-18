package com.alkes.alkse.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class AdminController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; //  Akan merender src/main/resources/templates/login.html
    }



//    @GetMapping("/admin/dashboard")
//    public String dashboardPage() {
//        return "admin/dashboard"; // Akan merender src/main/resources/templates/admin/dashboard.html
//    }

    @GetMapping("/admin/index")
    public String homePage() {
        return "admin/index"; // Akan merender src/main/resources/templates/admin/index.html
    }
}
