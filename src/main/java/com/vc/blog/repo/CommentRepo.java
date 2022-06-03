package com.vc.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vc.blog.entity.Comments;

@Repository
public interface CommentRepo extends JpaRepository<Comments, Long>{

}
