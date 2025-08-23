package com.alkes.alkse.controller;

import com.alkes.alkse.model.Admin;
import com.alkes.alkse.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/admin/setadmin")
public class AdminController {


    @Autowired
    private AdminService adminService;
    @GetMapping
    public String listAdmins(Model model) {
        model.addAttribute("admins", adminService.findAll());
        return "admin/setadmin/list";
    }


    @GetMapping("/form") // <--- PERBAIKI URL DI SINI
    public String showCreateForm(Model model) {
        model.addAttribute("admin", new Admin());
        // HANYA KEMBALIKAN NAMA FOLDER & FILE RELATIF DARI 'templates'
        return "admin/setadmin/form"; // <--- PERBAIKI JALUR DI SINI
    }

    @PostMapping("/save")
    public String saveAdmin(@ModelAttribute Admin admin) {
        adminService.saveAdmin(admin);
        return "redirect:/admin/setadmin";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Admin> admin = adminService.findByIdAdmin(id);
        if (admin.isPresent()) {
            model.addAttribute("admin", admin.get());
            return "admin/setadmin/form";
        }
        return "redirect:/admin/setadmin";
    }

    @PostMapping("/update/{id}")
    public String updateAdmin(@PathVariable Long id, @ModelAttribute Admin admin) {
        adminService.updatePassword(id, admin.getPassword());
        return "redirect:/admin/setadmin";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteById(id);
        return "redirect:/admin/setadmin";
    }

//
//
//
////    @GetMapping("/admin/dashboard")
////    public String dashboardPage() {
////        return "admin/dashboard"; // Akan merender src/main/resources/templates/admin/dashboard.html
////    }
//

}
