package com.alkes.alkse.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.alkes.alkse.dto.ProductFormDTO;
import com.alkes.alkse.model.Product;
import com.alkes.alkse.service.ProductService;
import com.alkes.alkse.service.CategoryService;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Value("${file.upload-dir}")
    private String uploadDirConfig;

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listProducts(Model model) {
        // Gunakan nama metode yang konsisten
        model.addAttribute("products", productService.findAllProducts());
        return "admin/product/list";
    }

    @GetMapping("/form")
    public String showProductForm(@RequestParam(required = false) Long id, Model model) {
        ProductFormDTO productFormDto = new ProductFormDTO();
        if (id != null) {
            Product product = productService.findProductById(id).orElseThrow();
            productFormDto.setId(product.getId());
            productFormDto.setName(product.getName());
            productFormDto.setDescription(product.getDescription());
            productFormDto.setPrice(product.getPrice());
            productFormDto.setImageUrl(product.getImageUrl());
            productFormDto.setCategory(product.getCategory());
        }
        model.addAttribute("productFormDto", productFormDto);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "admin/product/form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("productFormDto") @Valid ProductFormDTO productFormDto,
                              BindingResult result,
                              Model model) {

        if (result.hasErrors()) {
            // Jika ada error validasi, muat kembali form dengan data yang sudah ada
            // Pastikan Anda memuat ulang model yang diperlukan, seperti 'categories'
            model.addAttribute("categories", categoryService.findAllCategories());
            return "admin/product/form";
        }

        Product product;
        if (productFormDto.getId() != null) {
            // Mode Edit: Ambil produk yang sudah ada dari database
            product = productService.findProductById(productFormDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + productFormDto.getId()));
        } else {
            // Mode Tambah: Buat instance produk baru
            product = new Product();
        }

        // Tetapkan properti dari DTO ke entitas Product
        product.setName(productFormDto.getName());
        product.setDescription(productFormDto.getDescription());
        product.setPrice(productFormDto.getPrice());
        product.setCategory(productFormDto.getCategory());

        MultipartFile imageFile = productFormDto.getImageFile();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String productUploadDir = uploadDirConfig + "/products";
                Path uploadDir = Paths.get(productUploadDir);

                if (!Files.exists(uploadDir)){
                    Files.createDirectories(uploadDir);
                }
                String fileName = UUID.randomUUID().toString() + "-" + imageFile.getOriginalFilename();
                Path filePath = uploadDir.resolve(fileName);
                Files.copy(imageFile.getInputStream(), filePath);

                // Simpan URL relatif ke entitas produk
                product.setImageUrl("/uploads/products/" + fileName);

            } catch (IOException e) {
                // Tangani pengecualian I/O, misalnya dengan mencatat error
                e.printStackTrace();
                // Anda bisa tambahkan pesan error ke model jika perlu
                return "admin/product/form";
            }
        } else if (productFormDto.getImageUrl() != null) {
            // Jika tidak ada file baru diunggah, pertahankan URL gambar yang sudah ada
            product.setImageUrl(productFormDto.getImageUrl());
        }

        // Simpan entitas produk yang sudah diperbarui atau baru
        productService.saveProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/product";
    }

}
