package com.vc.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vc.blog.entity.Users;
import com.vc.blog.exceptions.ResourceNotFoundException;
import com.vc.blog.payloads.UserDto;
import com.vc.blog.repo.UserRepo;
import com.vc.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	private static ModelMapper mapper;

	@Override
	public UserDto createUser(UserDto user) {
		mapper = new ModelMapper();
		Users mappedUser = mapper.map(user, Users.class);
		Users savedUser = userRepo.save(mappedUser);
		return mapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto user, Long userId) {
		mapper = new ModelMapper();
		userRepo.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));
		
		Users mappedUser = mapper.map(user, Users.class);
		Users savedUser = userRepo.save(mappedUser);
		return mapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(Long userId) {
		mapper = new ModelMapper();
		Users users = userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("USER", "ID", userId));
		return mapper.map(users, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		mapper = new ModelMapper();
		List<Users> allUsers = userRepo.findAll();
		if(allUsers.isEmpty())
			throw new ResourceNotFoundException();
		List<UserDto> allUserDto = allUsers.stream()
				.map(user -> mapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		return allUserDto;
	}

	@Override
	public void deleteUser(Long userId) {
		mapper = new ModelMapper();
		Users entity = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));
		userRepo.delete(entity);
	}

}
