package com.example.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class CustomExceptionHandler {
	
	public ModelAndView handleException(FileStorageException fileStorageException, RedirectAttributes redirectAttributes) {
		ModelAndView mv=new ModelAndView();
		mv.addObject("message",fileStorageException.getMessage());
		mv.setViewName("error");
		return mv;
	}

}
