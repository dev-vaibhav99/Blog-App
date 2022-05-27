package com.vc.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vc.blog.entity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

}
