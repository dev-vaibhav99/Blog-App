package com.vc.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vc.blog.payloads.CategoryDto;

@Service
public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto, Long id);
	
	CategoryDto getCategoryById(Long categoryId);
	
	List<CategoryDto> getAllCategories();
	
	void deleteCategory(Long categoryId);
}
