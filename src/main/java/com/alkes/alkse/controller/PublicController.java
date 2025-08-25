package com.alkes.alkse.controller;
import com.alkes.alkse.model.About;
import com.alkes.alkse.service.ProductService;
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
    private final ProductService productService;
    public PublicController(AboutService aboutService, ProductService productService) {
        this.aboutService = aboutService;
        this.productService = productService;
    }

    @GetMapping
    public String index(Model model) {
        About about = aboutService.getAbout();
        model.addAttribute("about", about);
        model.addAttribute("products", productService.getTop3Products());
        model.addAttribute("activePage", "home");
        return "/index"; // file src/main/resources/templates/public/index.html
    }

    @GetMapping("/about")
    public String aboutUs(Model model) {
        About about = aboutService.getAbout();
        model.addAttribute("about", about);
        model.addAttribute("activePage", "about");
        return "about"; // file src/main/resources/templates/public/about.html
    }

    @GetMapping("/blog")
    public String blogus(Model model) {
        About about = aboutService.getAbout();
        model.addAttribute("about", about);
        model.addAttribute("activePage", "blog");
        return "blog"; // file src/main/resources/templates/public/blog.html
    }

    @GetMapping("/countactus")
    public String countactus(Model model) {
        About about = aboutService.getAbout();
        model.addAttribute("about", about);
        model.addAttribute("activePage", "countactus");
        return "countactus"; // file src/main/resources/templates/public/blog.html
    }

    @GetMapping("/services")
    public String servicesus(Model model) {
        About about = aboutService.getAbout();
        model.addAttribute("about", about);
        model.addAttribute("activePage", "services");
        return "services"; // file src/main/resources/templates/public/blog.html
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        About about = aboutService.getAbout();
        model.addAttribute("about", about);
        model.addAttribute("products", productService.findAllProducts());
        model.addAttribute("activePage", "shop");
        return "shop"; // file src/main/resources/templates/public/blog.html
    }
}
