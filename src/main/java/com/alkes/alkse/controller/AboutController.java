package com.alkes.alkse.controller;
import com.alkes.alkse.model.About;
import com.alkes.alkse.dto.AboutFormDTO;
import com.alkes.alkse.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Controller
@RequestMapping("/admin/about")
public class AboutController {

    @Value("${file.upload-dir}")
    private String uploadPath;

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
        model.addAttribute("activePage", "about");

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
    public String ShowAboutForm(@RequestParam(required = false) Long id, Model model){
        AboutFormDTO aboutFormDTO = new AboutFormDTO();
        if(id != null){
            About about = aboutService.findById(id).orElseThrow();
            aboutFormDTO.setId(about.getId());
            aboutFormDTO.setCompanyname(about.getCompanyname());
            aboutFormDTO.setDescription(about.getDescription());
            aboutFormDTO.setLogoUrl(about.getLogoUrl());
            aboutFormDTO.setMission(about.getMission());
            aboutFormDTO.setVision(about.getVision());
            aboutFormDTO.setContactEmail(about.getContactEmail());
            aboutFormDTO.setAddress(about.getAddress());
            aboutFormDTO.setPhone(about.getPhone());
            aboutFormDTO.setSlogan(about.getSlogan());
        }

        model.addAttribute("aboutFormDTO", aboutFormDTO);
        return "admin/about/form";
    }

    @PostMapping("/save")
    public String saveAbout(@Valid @ModelAttribute("aboutFormDTO") AboutFormDTO aboutFormDTO, BindingResult result) {

        if (result.hasErrors()){
            return "admin/about/form";
        }

        About about;
        if(aboutFormDTO.getId() != null){
            about = aboutService.findById(aboutFormDTO.getId()).orElseThrow(() -> new IllegalArgumentException("About not found"));
        } else {
            // Jika tidak ada ID, buat objek baru
            about = new About();
        }


        String logoUrl = about.getLogoUrl(); // Dapatkan URL yang sudah ada (jika ada)
        MultipartFile logoFile = aboutFormDTO.getLogoFile();

        if (logoFile != null && !logoFile.isEmpty()) {
            try {
                String AbotUploadDir = uploadPath + "/logos";
                Path uploadPath = Paths.get(AbotUploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String fileName = UUID.randomUUID().toString() + "_" + logoFile.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(logoFile.getInputStream(), filePath);
                logoUrl = "/uploads/logos/" + fileName;
            } catch (IOException e) {
                // Log the error and return to form with an error message
                e.printStackTrace();
                return "redirect:/admin/about/form?error";
            }
        }

        // Salin data dari DTO ke entitas About
        about.setCompanyname(aboutFormDTO.getCompanyname());
        about.setDescription(aboutFormDTO.getDescription());
        about.setLogoUrl(logoUrl);
        about.setMission(aboutFormDTO.getMission());
        about.setVision(aboutFormDTO.getVision());
        about.setContactEmail(aboutFormDTO.getContactEmail());
        about.setAddress(aboutFormDTO.getAddress());
        about.setPhone(aboutFormDTO.getPhone());
        about.setSlogan(aboutFormDTO.getSlogan());

        // Simpan entitas ke database
        aboutService.saveAbout(about);

        return "redirect:/admin/about";
    }

    @GetMapping("/list")
    public String listAbout(Model model) {
        List<About> aboutList = aboutService.findAll();
        model.addAttribute("aboutList", aboutList);
        return "admin/about/list";
    }
}







