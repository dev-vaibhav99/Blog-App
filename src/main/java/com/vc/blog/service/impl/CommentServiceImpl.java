package com.vc.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vc.blog.entity.Comments;
import com.vc.blog.entity.Posts;
import com.vc.blog.exceptions.ResourceNotFoundException;
import com.vc.blog.payloads.CommentDto;
import com.vc.blog.repo.CommentRepo;
import com.vc.blog.repo.PostRepo;
import com.vc.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;
	
	private ModelMapper mapper = new ModelMapper();;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Long postId) {
		Posts post = postRepo.findById(postId).
				orElseThrow(()->new ResourceNotFoundException("POST", "POST ID", postId));
		Comments commentEntity = mapper.map(commentDto, Comments.class);
		commentEntity.setPost(post);
		Comments savedComment = commentRepo.save(commentEntity);
		return mapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Long id) {
		commentRepo.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("COMMENT", "COMMENT ID", id));
	}

	
}
