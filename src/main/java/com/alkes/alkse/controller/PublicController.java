package com.alkes.alkse.controller;
import com.alkes.alkse.model.About;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.alkes.alkse.service.AboutService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class PublicController {

    private final AboutService aboutService;

    public PublicController(AboutService aboutService) {
        this.aboutService = aboutService;
    }

    @GetMapping
    public String index(Model model) {
        About about = aboutService.getAbout();
        model.addAttribute("about", about);
        return "/index"; // file src/main/resources/templates/public/index.html
    }
}
