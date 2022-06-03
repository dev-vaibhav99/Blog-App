package com.vc.blog.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vc.blog.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile image) throws IOException {
		// File name
		String name = image.getOriginalFilename();
		
		// random name generator
		String randomId = UUID.randomUUID().toString();
		String randomName = randomId.concat(name.substring(name.lastIndexOf(".")));

		// Full path
		String filePath = path + File.separator + randomName;
				
		// Create folder if not created
		File file = new File(name);		
		if(!file.exists()) {
			file.mkdir();
		}
		
		// File copy
		Files.copy(image.getInputStream(), Paths.get(filePath));
		
		return randomName;
	}

	@Override
	public InputStream getImage(String path, String fileName) throws FileNotFoundException {
		String fullPath = path+File.separator+fileName;
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}

}
