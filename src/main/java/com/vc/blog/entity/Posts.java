package com.vc.blog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50, nullable = false)
	private String title;
	@Column(length = 1000, nullable = true)
	private String content;
	private String imageName;
	private Date addedDate;
	
	// for relation
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Categories category;
	
	@ManyToOne
	private Users user;
}
