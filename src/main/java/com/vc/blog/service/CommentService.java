package com.vc.blog.service;

import org.springframework.stereotype.Service;

import com.vc.blog.payloads.CommentDto;

@Service
public interface CommentService {

	
	CommentDto createComment(CommentDto commentDto, Long postId);
	
	void deleteComment(Long id);
}
