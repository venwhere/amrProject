package com.huang.microboot.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class SampleController {
    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello sunday";
    }
    @RequestMapping("/eche")
    @ResponseBody
    public String eche(HttpServletRequest request) {
    	System.out.println(request.getSession().getId());
    	System.err.println(request.getServletContext().getRealPath("/"));
    	System.err.println(request.getRemoteAddr());
    	return "i";
    }
}















