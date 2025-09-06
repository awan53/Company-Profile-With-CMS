package com.alkes.alkse.controller;

import com.alkes.alkse.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
@ControllerAdvice
public class GlobalControllerAdvice {
    @Autowired
    private AboutService aboutService;

    @ModelAttribute
    public void addGlobalAttributes(Model model) {
        model.addAttribute("about", aboutService.getAbout());
    }
}
