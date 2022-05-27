package com.vc.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vc.blog.entity.Categories;
import com.vc.blog.exceptions.ResourceNotFoundException;
import com.vc.blog.payloads.CategoryDto;
import com.vc.blog.repo.CategoryRepo;
import com.vc.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	private ModelMapper mapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		mapper = new ModelMapper();
		Categories categoryEntity = mapper.map(categoryDto, Categories.class);
		Categories savedCategory = categoryRepo.save(categoryEntity);
		return mapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		mapper = new ModelMapper();
		Categories categoryEntity = mapper.map(categoryDto, Categories.class);
		categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("CATEGORY", "CATEGORY ID", categoryId));
		Categories savedCategory = categoryRepo.save(categoryEntity);
		return mapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId) {
		mapper = new ModelMapper();
		Categories categories = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("CATEGORY", "CATEGORY ID", categoryId));
		return mapper.map(categories, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		mapper = new ModelMapper();
		List<Categories> list = categoryRepo.findAll();
		List<CategoryDto> categoryDtos = list.stream()
			.map((category)-> mapper.map(category, CategoryDto.class))
			.collect(Collectors.toList());
		return categoryDtos;
	}

	@Override
	public void deleteCategory(Long categoryId) {
		mapper = new ModelMapper();
		Categories category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("CATEGORY","CATEGORY ID", categoryId));
		categoryRepo.delete(category);
	}

}
