package com.alkes.alkse.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.alkes.alkse.repository.BlogRepository;
import com.alkes.alkse.model.Blog;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<Blog> getTop4ByOrderByIdDesc(){return blogRepository.findTop4ByOrderByIdDesc();}

    public Optional<Blog> findByIdBlog(Long id) {
        return blogRepository.findById(id);
    }

    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }
    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }

    public Page<Blog> findPaginatedBlogs(Pageable pageable){
        return blogRepository.findAll(pageable);
    }

    public Page<Blog> searchBlogs(String keyword, Pageable pageable){
        if (keyword != null && !keyword.isEmpty()) {
            return blogRepository.findByTitleContainingIgnoreCase(keyword, pageable);
        }
        return blogRepository.findAll(pageable);
    }





}
