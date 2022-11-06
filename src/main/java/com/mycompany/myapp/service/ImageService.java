package com.mycompany.myapp.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
	
	private Path storage = Paths.get("c:\\images\\");
	
	public Path create(MultipartFile file) throws IOException {
		Path path = storage.resolve(Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();
		path = renamePolicy(path);
		Files.copy(file.getInputStream(), path);
		return path;
	}
	
	public Resource read(String filename) throws MalformedURLException {
		Path path = storage.resolve(Paths.get(filename)).normalize().toAbsolutePath();
		Resource resource = new UrlResource(path.toUri());
		return resource;
	}
	
	private Path renamePolicy(Path path) {
		String name = path.getFileName().toString();
		String body = null;
		String ext = null;
		
		int dot = name.lastIndexOf(".");
		if (dot == -1) {
			body = name;
			ext = "";
		} else {
			body = name.substring(0, dot);
			ext = name.substring(dot);
		}
		
		int count = 0;
		Path parentPath = path.getParent();
		while(Files.exists(path)) {
			String newName = body + (count++) + ext;
			path = parentPath.resolve(Paths.get(newName)).normalize().toAbsolutePath();
		}
		return path;
	}
	
}
