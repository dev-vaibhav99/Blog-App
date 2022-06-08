package com.vc.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	
	private Long id;
	
	@NotBlank(message = "Category Title can't be blank")
	private String categoryTitle;
	
	@NotBlank(message = "Category Description can't be blank")
	@Size(min = 3, max = 100, message = "Category Description must be min of 3 and max of 100 characters")
	private String categoryDescription;
}
