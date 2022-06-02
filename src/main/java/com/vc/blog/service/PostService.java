package com.vc.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vc.blog.payloads.PostDto;
import com.vc.blog.payloads.PostResponse;

@Service
public interface PostService {

	PostDto createPost(PostDto dto, Long userId, Long categoryId);
	
	PostDto updatePost(PostDto dto, Long id);
	
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	PostDto getPostById(Long id);
	
	void deletePost(Long id);
	
	List<PostDto> getAllPostsByCategory(Long categoryId);
//	
	List<PostDto> getAllPostsByUser(Long userId);
	
	List<PostDto> searchPosts(String keywords);
	
	
}
