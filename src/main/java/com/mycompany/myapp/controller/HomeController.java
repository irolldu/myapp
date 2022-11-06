package com.mycompany.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myapp.dto.PostListDto;
import com.mycompany.myapp.service.PostService;

@Controller
public class HomeController {
	
	@Autowired
	private PostService postService;
	
	public PostListDto postList() {
		PostListDto postListDto = new PostListDto();
		postListDto.setOrderBy_field("ID");
		postListDto.setOrderBy_direction("DESC");
		postListDto.setPage(1);
		postListDto.setPageSize(5);
		postService.list(postListDto, false);
		return postListDto;
	}
	
	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		mv.addObject("postListDto", postList());
		return mv;
	}
	
}
