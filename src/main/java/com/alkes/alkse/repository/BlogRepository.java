package com.alkes.alkse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alkes.alkse.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Page<Blog> findAll(Pageable pageable);

    List<Blog> findTop4ByOrderByIdDesc();

    Page<Blog>findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

}
