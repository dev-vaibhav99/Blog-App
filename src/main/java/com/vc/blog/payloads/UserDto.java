package com.vc.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

	private Long id;
	
	@NotBlank(message = "Name must have atleast 4 characters")
	@Size(min = 4)
	private String name;
	
	@Email(message = "Invalid E-Mail Address")
	private String email;
	
	@NotBlank
	@Size(min = 8, max = 15, message = "Password must be min of 8 and max of 15 characters")
	private String password;
	
	@NotBlank
	private String about;	
}
