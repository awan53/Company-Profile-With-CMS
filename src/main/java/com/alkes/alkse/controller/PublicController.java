package com.alkes.alkse.controller;
import com.alkes.alkse.model.About;
import com.alkes.alkse.service.BlogService;
import com.alkes.alkse.service.ProductService;
import com.alkes.alkse.model.ContactInfo;
import com.alkes.alkse.service.ContactInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.alkes.alkse.service.AboutService;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class PublicController {

    private final AboutService aboutService;
    private final ProductService productService;
    private final BlogService blogService;
    private ContactInfoService contactInfoService;

    public PublicController(AboutService aboutService, ProductService productService, BlogService blogService,
                            ContactInfoService contactInfoService) {

        this.aboutService = aboutService;
        this.productService = productService;
        this.blogService = blogService;
        this.contactInfoService = contactInfoService;
    }

    @GetMapping
    public String index(Model model) {
        About about = aboutService.getAbout();
        model.addAttribute("about", about);
        model.addAttribute("products", productService.getTop3Products());
        model.addAttribute("activePage", "home");
        model.addAttribute("blogs", blogService.getTop3ByOrderByIdDesc());
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
        model.addAttribute("blogs", blogService.findAllBlog());
        model.addAttribute("activePage", "blog");

        return "blog"; // file src/main/resources/templates/public/blog.html
    }

    @GetMapping("/blog/{id}")
    public String blogbyidDetail(@PathVariable Long id, Model model) {
        About about = aboutService.getAbout();
        model.addAttribute("about", about);
        model.addAttribute("activePage", "blog");

        return blogService.findByIdBlog(id)
                .map(blog -> {
                    model.addAttribute("blog", blog);
                    return "public/blogdetail"; // sesuai folder templates/public/
                })
                .orElse("public/error-404");
    }

    // ✅ Tampilkan form Contact Us
    @GetMapping("/countactus")
    public String countactus(Model model) {

        model.addAttribute("contactInfo", new ContactInfo());
        model.addAttribute("activePage", "countactus");
        return "countactus";
        // file src/main/resources/templates/public/blog.html

    }

    // ✅ Proses kirim pesan Contact Us
    @PostMapping("/contact")
    public String sendContact(@ModelAttribute("contactInfo") ContactInfo contactInfo,
                              Model model) {
        contactInfoService.saveAndNotify(contactInfo);
        model.addAttribute("successMessage", "Pesan Anda berhasil dikirim. Admin akan segera menghubungi Anda.");
        model.addAttribute("activePage", "contactus");
        return "countactus"; // kembali ke form dengan pesan sukses
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

    @GetMapping("/shop/{id}")
    public String shopbyidDetail(@PathVariable Long id, Model model) {
        About about = aboutService.getAbout();
        model.addAttribute("about", about);
        model.addAttribute("activePage", "products");

        return productService.findProductById(id).map(product -> {model.addAttribute("product", product);
            return "public/shopdetail"; // sesuai folder templates/public/
        }).orElse("public/error-404");
    }
}
