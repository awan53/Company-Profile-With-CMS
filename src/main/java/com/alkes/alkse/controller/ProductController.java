package com.alkes.alkse.controller;
import com.alkes.alkse.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import java.util.List;
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
    public String listProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
                               @RequestParam(required = false) String q,
                               Model model) {
      Page<Product> productPage;
      if (q != null && !q.isEmpty()){
          productPage = productService.searchProducts(q, PageRequest.of(page, size));
          model.addAttribute("keyword", q);
        } else {
            productPage = productService.findPaginatedProducts(PageRequest.of(page, size));
            model.addAttribute("keyword", "");
      }


        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());

        return "admin/product/list";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Product> search(@RequestParam String q){
        return productService.searchProduct(q);
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
            if (product.getCategory() != null) {
                productFormDto.setCategoryId(product.getCategory().getId()); // <<< penting
            }
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
            model.addAttribute("categories", categoryService.findAllCategories());
            return "admin/product/form";
        }

        Product product;
        boolean isUpdate = productFormDto.getId() != null;

        if (isUpdate) {
            // Mode Edit
            product = productService.findProductById(productFormDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + productFormDto.getId()));
        } else {
            // Mode Tambah
            product = new Product();
        }

        // Set field biasa
        product.setName(productFormDto.getName());
        product.setDescription(productFormDto.getDescription());
        product.setPrice(productFormDto.getPrice());

        // Set kategori
        if (productFormDto.getCategoryId() != null) {
            Category category = categoryService.findCategoryById(productFormDto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + productFormDto.getCategoryId()));
            product.setCategory(category);
        }

        // Upload image
        MultipartFile imageFile = productFormDto.getImageFile();

        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                // Upload gambar baru
                String productUploadDir = uploadDirConfig + "/products";
                Path uploadDir = Paths.get(productUploadDir);

                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }
                String fileName = UUID.randomUUID().toString() + "-" + imageFile.getOriginalFilename();
                Path filePath = uploadDir.resolve(fileName);
                Files.copy(imageFile.getInputStream(), filePath);

                product.setImageUrl("/uploads/products/" + fileName);

            } else {
                if (isUpdate) {
                    // Update tapi tidak upload gambar baru → tetap pakai lama
                    product.setImageUrl(productFormDto.getImageUrl());
                } else {
                    // Create tapi tidak ada gambar → tolak
                    model.addAttribute("categories", categoryService.findAllCategories());
                    model.addAttribute("errorMessage", "Image is required for new product");
                    return "admin/product/form";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("categories", categoryService.findAllCategories());
            model.addAttribute("errorMessage", "Failed to upload image");
            return "admin/product/form";
        }

        // Simpan produk
        productService.saveProduct(product);
        return "redirect:/admin/product";
    }



    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/product";
    }

}
