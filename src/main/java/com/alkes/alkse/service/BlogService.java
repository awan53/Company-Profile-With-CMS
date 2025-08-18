package com.alkes.alkse.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.alkes.alkse.repository.BlogRepository;
import com.alkes.alkse.model.Blog;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    private BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> findAllBlog() {
        return blogRepository.findAll();
    }

    public Optional<Blog> findByIdBlog(Long id) {
        return blogRepository.findById(id);
    }

    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }
    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }


}
