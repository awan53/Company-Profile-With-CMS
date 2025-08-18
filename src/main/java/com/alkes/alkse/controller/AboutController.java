package com.alkes.alkse.controller;
import com.alkes.alkse.model.About;
import com.alkes.alkse.repository.AboutRepository;
import com.alkes.alkse.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping("/admin/about")
public class AboutController {


    private final AboutService aboutService;

    @Autowired
    public AboutController (AboutService aboutService) {
        this.aboutService = aboutService;
    }

    @GetMapping
    public String aboutPage(Model model) {
        About about = new About();
        List<About> aboutList = aboutService.findAll();
        model.addAttribute("about", about);
        model.addAttribute("aboutList", aboutList);

        return "admin/about/list";
    }

//    @GetMapping("/form")
//    public String showAboutForm(@RequestParam(required = false) Long id, Model model) {
//        About about;
//        if (id != null) {
//            about = aboutService.findById(id).orElseThrow();
//        } else {
//            about = aboutService.findById(1L).orElse(new About());
//        }
//        model.addAttribute("about", about);
//        return "admin/about/form";
//    }

    @GetMapping("/form")
    public String ShowAboutForm(Model model){
        About about = aboutService.findById(1L).orElse(new About());
        model.addAttribute("about", about);
        return "admin/about/form";
    }

    @PostMapping("/save")
    public String saveAbout(@ModelAttribute About about) {

          aboutService.saveAbout(about);
        return "redirect:/admin/about/list";
    }

    @GetMapping("/list")
    public String listAbout(Model model) {
        List<About> aboutList = aboutService.findAll();
        model.addAttribute("aboutList", aboutList);
        return "admin/about/list";
    }
}







