package com.alkes.alkse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alkes.alkse.model.Blog;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
