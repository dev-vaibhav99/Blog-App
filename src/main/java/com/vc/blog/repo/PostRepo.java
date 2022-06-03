package com.vc.blog.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vc.blog.entity.Categories;
import com.vc.blog.entity.Posts;
import com.vc.blog.entity.Users;

@Repository
public interface PostRepo extends JpaRepository<Posts, Long>{

	List<Posts> findByUser(Users user);
	List<Posts> findByCategory(Categories categories);
	
	@Query("select p from Posts p where p.title like :key")
	List<Posts> searchByTitle(@Param("key") String title);
	
	
}
