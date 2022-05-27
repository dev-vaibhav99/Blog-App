package com.vc.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vc.blog.entity.Categories;

public interface CategoryRepo extends JpaRepository<Categories, Long> {

}
