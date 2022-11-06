package com.mycompany.myapp.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ModelAndView error(Exception exception) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error");
		mv.addObject("message", exception.getMessage());
		return mv;
	}
	
}
