package com.vc.blog.security;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vc.blog.entity.Users;
import com.vc.blog.exceptions.ResourceNotFoundException;
import com.vc.blog.repo.UserRepo;


@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	private ModelMapper mapper = new ModelMapper();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// loading user from database by username
		Users users = userRepo.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("USER", "EMAIL : " + username,0L));

		return users;
	}

}