package com.huang.microboot.config;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class GlobalExceptionHandler {
	public static final String DEFAULT_ERROR_VIEW="pages/error";
	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandler(HttpServletRequest req,Exception e) {
		ModelAndView mav=new ModelAndView(DEFAULT_ERROR_VIEW);
		mav.addObject("exception",e);
		mav.addObject("url",req.getRequestURL());
		return mav;
	}
}
