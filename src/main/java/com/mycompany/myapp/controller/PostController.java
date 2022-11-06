package com.mycompany.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.mycompany.myapp.component.PostFilterList;
import com.mycompany.myapp.converter.PostCreateUpdateDtoToPostConverter;
import com.mycompany.myapp.converter.PostToPostCreateUpdateDtoConverter;
import com.mycompany.myapp.converter.PostToPostReadDtoConverter;
import com.mycompany.myapp.dto.PostCreateUpdateDto;
import com.mycompany.myapp.dto.PostListDto;
import com.mycompany.myapp.model.Post;
import com.mycompany.myapp.service.PostService;
import com.mycompany.myapp.validator.PostValidator;

@Controller
public class PostController {
	
	@Autowired
	private PostFilterList postFilterList;
	@Autowired
	private PostValidator postValidator;
	@Autowired
	private PostService postService;
	@Autowired
	private PostToPostReadDtoConverter postToPostReadDtoConverter;
	@Autowired
	private PostToPostCreateUpdateDtoConverter postToPostCreateUpdateDtoConverter;
	@Autowired
	private PostCreateUpdateDtoToPostConverter postCreateUpdateDtoToPostConverter;
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_MANAGER')")
	@GetMapping("/post/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("post/create");
		mv.addObject("postFilterList", postFilterList);
		return mv;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_MANAGER')")
	@PostMapping("/post/create")
	public ModelAndView create(PostCreateUpdateDto postCreateDto, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		postValidator.validate(postCreateDto, bindingResult);
		if (bindingResult.hasErrors()) {
			mv.setViewName("post/create");
			mv.addObject("postFilterList", postFilterList);
			mv.addObject("dto", postCreateDto);
			mv.addObject("errors", bindingResult);
		} else {
			Post post = postCreateUpdateDtoToPostConverter.convert(postCreateDto);
			postService.create(post);
			mv.addObject("returnUrl", MvcUriComponentsBuilder.fromMethodName(PostController.class, "read", post.getId()).build().toUri().toString());
			mv.setViewName("post/createSuccess");
		}
		return mv;
	}
	
	@GetMapping("/post/read/{id}")
	public ModelAndView read(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView();
		Post post = postService.read(id);
		mv.setViewName("post/read");
		mv.addObject("dto", postToPostReadDtoConverter.convert(post));
		return mv;
	}
	
	@GetMapping("/post/update/{id}")
	public ModelAndView update(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView();
		Post post = postService.read(id);
		PostCreateUpdateDto postUpdateDto = postToPostCreateUpdateDtoConverter.convert(post);
		mv.setViewName("post/update");
		mv.addObject("postFilterList", postFilterList);
		mv.addObject("dto", postUpdateDto);
		return mv;
	}
	
	@PostMapping("/post/update/{id}")
	public ModelAndView update(PostCreateUpdateDto postUpdateDto, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		postValidator.validate(postUpdateDto, bindingResult);
		if (bindingResult.hasErrors()) {
			mv.setViewName("post/update");
			mv.addObject("postFilterList", postFilterList);
			mv.addObject("dto", postUpdateDto);
			mv.addObject("errors", bindingResult);
		} else {
			Post post = postCreateUpdateDtoToPostConverter.convert(postUpdateDto);
			if (postService.update(post)) {
				mv.setViewName("post/updateSuccess");
				mv.addObject("returnUrl", MvcUriComponentsBuilder.fromMethodName(PostController.class, "read", postUpdateDto.getId()).build().toUri().toString());
			} else {
				mv.setViewName("post/updateFail");
				mv.addObject("returnUrl", MvcUriComponentsBuilder.fromMethodName(PostController.class, "update", postUpdateDto.getId()).build().toUri().toString());
			}
		}
		return mv;
	}
	
	@PostMapping("/post/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Integer id) {
		postService.delete(id);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("post/deleteSuccess");
		return mv;
	}
	
	@GetMapping("/post/list")
	public ModelAndView list(PostListDto postListDto) {
		postService.list(postListDto, false);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("post/list");
		mv.addObject("postFilterList", postFilterList);
		mv.addObject("dto", postListDto);
		return mv;
	}
	
}
