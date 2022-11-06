package com.mycompany.myapp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myapp.converter.UserCreateUpdateDtoToUserConverter;
import com.mycompany.myapp.dto.PostListDto;
import com.mycompany.myapp.dto.UserCreateUpdateDto;
import com.mycompany.myapp.model.User;
import com.mycompany.myapp.service.PostService;
import com.mycompany.myapp.service.UserService;
import com.mycompany.myapp.validator.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserValidator userValidator;
	@Autowired
	private UserService userService;
	@Autowired
	private UserCreateUpdateDtoToUserConverter userCreateUpdateDtoConverter;
	@Autowired
	private PostService postService;
	
	@GetMapping("/user/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/create");
		return mv;
	}
	
	@PostMapping("/user/create")
	public ModelAndView create(UserCreateUpdateDto userCreateDto, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		userValidator.validate(userCreateDto, bindingResult);
		if (bindingResult.hasErrors()) {
			mv.setViewName("user/create");
			mv.addObject("dto", userCreateDto);
			mv.addObject("errors", bindingResult);
		} else {
			User user = userCreateUpdateDtoConverter.convert(userCreateDto);
			userService.create(user);
			mv.setViewName("user/createSuccess");
		}
		return mv;
	}
	
	@GetMapping("/user/update")
	public ModelAndView update() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/update");
		return mv;
	}
	
	@PostMapping("/user/update")
	public ModelAndView update(UserCreateUpdateDto userUpdateDto, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		userValidator.validate(userUpdateDto, bindingResult);
		if (bindingResult.hasErrors()) {
			mv.setViewName("user/create");
			mv.addObject("dto", userUpdateDto);
			mv.addObject("errors", bindingResult);
		} else {
			User user = userCreateUpdateDtoConverter.convert(userUpdateDto);
			userService.update(user);
			mv.setViewName("redirect:/");
		}
		return mv;
	}
	
	@PostMapping("/user/delete")
	public ModelAndView delete(Principal principal) {
		userService.delete(((User) principal).getId());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "/user/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/login");
		return mv;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_MANAGER')")
	@GetMapping("/user/admin")
	public ModelAndView admin(PostListDto postListDto, @AuthenticationPrincipal User user) {
		postListDto.setUser(user);
		postService.list(postListDto, true);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/admin");
		mv.addObject("dto", postListDto);
		return mv;
	}
	
}
