package com.alkes.alkse.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.alkes.alkse.dto.BlogFormDTO;
import com.alkes.alkse.model.Blog;
import com.alkes.alkse.service.BlogService;


import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/blog")
public class BlogController {

    @Value("${file.upload-dir}")
    private String uploadDirConfig;

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public String listBlogs(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size, @RequestParam(required = false) String q, Model model) {

        Page<Blog> blogPage;
        if (q != null && !q.isEmpty()){
            blogPage = blogService.searchBlogs(q, PageRequest.of(page, size));
            model.addAttribute("keyword", q);
        } else {
            blogPage = blogService.findPaginatedBlogs(PageRequest.of(page, size));
            model.addAttribute("keyword","");
        }

        model.addAttribute("blogs", blogPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", blogPage.getTotalPages());

        return "admin/blog/list";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Blog> search(@RequestParam String q){
        return blogService.searchBlogs(q, Pageable.unpaged()).getContent();
    }

    @GetMapping("/form")
    public String showBlogForm(@RequestParam(required = false) Long id, Model model) {
        BlogFormDTO blogFormDto = new BlogFormDTO();
        if (id != null) {
            Blog blog = blogService.findByIdBlog(id).orElseThrow();
            blogFormDto.setId(blog.getId());
            blogFormDto.setTitle(blog.getTitle());
            blogFormDto.setDescription(blog.getDescription());
            blogFormDto.setIconUrl(blog.getIconUrl());

        }
        model.addAttribute("blogFormDto", blogFormDto);
        return "admin/blog/form";
    }

    @PostMapping("/save")
    public String saveBlog(@Valid @ModelAttribute("blogFormDto") BlogFormDTO blogFormDto, BindingResult result, Model model) {

        if(result.hasErrors()){
            return "admin/blog/form";
        }

        Blog blog;
        if (blogFormDto.getId() !=null){
            blog = blogService.findByIdBlog(blogFormDto.getId()).orElseThrow(()-> new IllegalArgumentException("Blog not found"));
        } else{
            blog = new Blog();
        }

        String imageUrl = blog.getIconUrl();
        MultipartFile iconFile = blogFormDto.getIconFile();

        if (iconFile != null && !iconFile.isEmpty()) {
            try {
                String blogUploadDir = uploadDirConfig + "/blogs";
                Path uploadPath = Paths.get(blogUploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String fileName = UUID.randomUUID().toString() + "_" + iconFile.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(iconFile.getInputStream(), filePath);
                imageUrl = "/uploads/blogs/" + fileName;
            } catch (IOException e) {
                return "redirect:/admin/blog/form?error";
            }
        } else {
            if (blogFormDto.getId() == null) {
                model.addAttribute("error", "Icon file is required for new blog entries.");
                return "admin/blog/form";
            }
        }
        blog.setTitle(blogFormDto.getTitle());
        blog.setDescription(blogFormDto.getDescription());
        blog.setIconUrl(imageUrl);

        blogService.saveBlog(blog);
        return "redirect:/admin/blog";
    }

    @GetMapping("/delete")
    public String deleteBlog(@RequestParam("id") Long id) {
        blogService.deleteById(id);
        return "redirect:/admin/blog";
    }
}
