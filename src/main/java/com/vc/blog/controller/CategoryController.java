package com.vc.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vc.blog.payloads.ApiResponse;
import com.vc.blog.payloads.CategoryDto;
import com.vc.blog.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/createCategory")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		return new ResponseEntity<CategoryDto>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
	}

	@PutMapping("/updateCategory/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Long id) {
		return new ResponseEntity<CategoryDto>(categoryService.updateCategory(categoryDto, id), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
		return new ResponseEntity<CategoryDto>(categoryService.getCategoryById(id), HttpStatus.FOUND);
	}

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCategories(), HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
		return new ResponseEntity<ApiResponse>(new ApiResponse("CATEGORY DELETED SUCCESSFULLY", true), HttpStatus.OK);
	}
}
