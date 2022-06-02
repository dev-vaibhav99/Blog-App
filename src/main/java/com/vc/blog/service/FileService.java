package com.vc.blog.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {

	String uploadImage(String path, MultipartFile image) throws IOException;
	InputStream getImage(String path, String fileName) throws FileNotFoundException;
}
