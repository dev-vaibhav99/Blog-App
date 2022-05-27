package com.vc.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vc.blog.entity.Categories;
import com.vc.blog.entity.Posts;
import com.vc.blog.entity.Users;
import com.vc.blog.exceptions.ResourceNotFoundException;
import com.vc.blog.payloads.PostDto;
import com.vc.blog.payloads.PostResponse;
import com.vc.blog.repo.CategoryRepo;
import com.vc.blog.repo.PostRepo;
import com.vc.blog.repo.UserRepo;
import com.vc.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	private static ModelMapper mapper = new ModelMapper();

	@Override
	public PostDto createPost(PostDto dto, Long userId, Long categoryId) {
		Users user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("USER", "USER ID", userId));

		Categories category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("CATEGORY", "CATEGORY ID", categoryId));

		Posts post = mapper.map(dto, Posts.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		return mapper.map(postRepo.save(post), PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto dto, Long id) {
		Posts post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("POST", "POST ID", id));
		post.setTitle(dto.getTitle());
		post.setContent(dto.getContent());
		post.setImageName(dto.getImageName());

		Posts updatedPost = postRepo.save(post);

		return mapper.map(updatedPost, PostDto.class);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable page = PageRequest.of(pageNumber, pageSize, sort);
		Page<Posts> pagePost = postRepo.findAll(page);
		List<Posts> allPosts = pagePost.getContent();

		if (allPosts.isEmpty())
			throw new ResourceNotFoundException();

		List<PostDto> allPostDto = allPosts.stream().map(post -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse(allPostDto, pagePost.getNumber(), pagePost.getSize(),
				pagePost.getTotalElements(), pagePost.getTotalPages(), pagePost.isLast());

		return postResponse;
	}

	@Override
	public PostDto getPostById(Long id) {
		Posts posts = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("POST", "POST ID", id));
		return mapper.map(posts, PostDto.class);
	}

	@Override
	public void deletePost(Long id) {
		Posts entity = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("POST", "POST ID", id));
		postRepo.delete(entity);
	}

//	@Override
//	public PostResponse getAllPostsByCategory(Long categoryId, Integer pageNumber, Integer pageSize) {
//		Pageable page = PageRequest.of(pageNumber, pageSize);
//		Page<Categories> pagePostByCategory = categoryRepo.findAll(page)
//				.orElseThrow(() -> new ResourceNotFoundException("CATEGORY", "CATEGORY ID", categoryId));
//
//		List<Posts> postsByCategory = postRepo.findByCategory(category  );
//
//		postRepo.findAll(Example.of(), page);
//
//		List<PostDto> postDtosByCategory = postsByCategory.stream().map(post -> mapper.map(post, PostDto.class))
//				.collect(Collectors.toList());
//
//		PostResponse postResponse = new PostResponse(postDtosByCategory, 0, 0, 0, 0, false);
//
//		return postResponse;
//	}

	@Override
	public PostResponse getAllPostsByUser(Long userId, Integer pageNumber, Integer pageSize) {
		PostResponse postResponse = new PostResponse();

		Users user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("USER", "USER ID", userId));

		List<Posts> postsByUser = postRepo.findByUser(user);

//		List<PostDto> postDtosByUser = postsByUser.stream()
//			.map(post -> mapper.map(post, PostDto.class))
//			.collect(Collectors.toList());
		return postResponse;
	}

	@Override
	public List<PostDto> searchPosts(String keywords) {
		List<Posts> posts = postRepo.searchByTitle("%" + keywords + "%");
		List<PostDto> searchedPostDtos = posts.stream().map((post) -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return searchedPostDtos;
	}

}
