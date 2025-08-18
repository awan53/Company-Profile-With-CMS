package com.alkes.alkse.controller;
import com.alkes.alkse.model.Category;
import com.alkes.alkse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        return "admin/category/list";
    }

    @GetMapping("/form")
    public String showCategoryForm(@RequestParam(required = false) Long id, Model model){
        Category category;
        if (id != null) {
            category = categoryService.findCategoryById(id).orElseThrow(()-> new IllegalArgumentException("Invalid category Id:" + id));
        } else {
            category = new Category();
        }
        model.addAttribute("category", category);
        return "admin/category/form";
    }

    @PostMapping("/save")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/category/form"; // Return to form if there are validation errors
        }
        categoryService.saveCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/category";
    }
}
        // Redirect to the category list after saving}}

