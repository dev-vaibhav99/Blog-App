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
import com.vc.blog.payloads.UserDto;
import com.vc.blog.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/saveUser")
	public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto) {
		UserDto dto = userService.createUser(userDto);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<UserDto> updateUser(@Valid@RequestBody UserDto userDto){
		UserDto updatedUser = userService.updateUser(userDto, userDto.getId());
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
		UserDto userById = userService.getUserById(id);
		return new ResponseEntity<UserDto>(userById, HttpStatus.FOUND);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> allUsers = userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(allUsers, HttpStatus.FOUND);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Long id){
		userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("USER DELETED SUCCESSFULLY", true), HttpStatus.OK);
	}
}
