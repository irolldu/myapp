package com.mycompany.myapp.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.mycompany.myapp.service.ImageService;

@RestController
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value = "/images", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> create(@RequestParam("file") MultipartFile mutipartFile) {
		try {
			String uri = MvcUriComponentsBuilder.fromMethodName(ImageController.class, "read", imageService.create(mutipartFile).getFileName().toString()).build().toUri().toString();
			return new ResponseEntity<>(uri, HttpStatus.OK);
		} catch (IllegalStateException | IOException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/images/{filename}", method = RequestMethod.GET)
	public ResponseEntity<Resource> read(@PathVariable("filename") String filename) {
		try {
			return new ResponseEntity<>(imageService.read(filename), HttpStatus.OK);
		} catch (MalformedURLException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
