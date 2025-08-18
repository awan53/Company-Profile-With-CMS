package com.alkes.alkse.dto;
import com.alkes.alkse.model.Blog;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;
public class BlogFormDTO {

    private Long id;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "description is required")
    private String description;
    private String iconUrl;
    private MultipartFile iconFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public MultipartFile getIconFile() {
        return iconFile;
    }

    public void setIconFile(MultipartFile iconFile) {
        this.iconFile = iconFile;
    }
}
