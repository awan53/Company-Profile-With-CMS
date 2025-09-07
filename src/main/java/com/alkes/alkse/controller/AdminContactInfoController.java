package com.alkes.alkse.controller;

import com.alkes.alkse.model.ContactInfo;
import com.alkes.alkse.service.ContactInfoService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/admin/contactinfo")
public class AdminContactInfoController {

    private final ContactInfoService contactInfoService;

   public AdminContactInfoController(ContactInfoService contactInfoService) {
        this.contactInfoService = contactInfoService;
    }

    //List Semua Pesan dari User
   @GetMapping
   public String listContactInfo(Model model) {
        model.addAttribute("messages", contactInfoService.findAll());
       return "admin/contactinfo/list";
   }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        ContactInfo contact = contactInfoService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pesan tidak ditemukan"));
        model.addAttribute("contact", contact);
        return "admin/contactinfo/detail";
    }

    @GetMapping("/{id}/reply")
    public String replyForm(@PathVariable Long id, Model model) {
        ContactInfo contact = contactInfoService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pesan tidak ditemukan"));
        model.addAttribute("contact", contact);
        return "admin/contactinfo/reply";
    }

    // Proses kirim reply
    @PostMapping("/{id}/reply")
    public String sendReply(@PathVariable Long id,
                            @RequestParam String subject,
                            @RequestParam String replyMessage) {
        ContactInfo contact = contactInfoService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pesan tidak ditemukan"));
        // kirim email balasan
        contactInfoService.sendReply(contact.getEmail(), subject, replyMessage);
        return "redirect:/admin/contactinfo?success";
    }

    @PostMapping("/{id}/delete")
    public String deleteContactInfo(@PathVariable Long id) {
         contactInfoService.deleteById(id);
         return "redirect:/admin/contactinfo";
    }
}
