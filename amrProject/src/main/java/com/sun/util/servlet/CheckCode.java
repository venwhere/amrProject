package com.sun.util.servlet;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class CheckCode extends HttpServlet {
	@Resource
	RedisTemplate<String,Object> redisTemplate;
	@RequestMapping("/CheckCode")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//HttpSession session = request.getSession();
		//String rand = (String) session.getAttribute("rand") ;
		String rand=(String) redisTemplate.opsForValue().get("rand");
		if (rand == null || "".equals(rand)) {
			response.getWriter().print(false);
		} else {
			response.getWriter().print(rand.equalsIgnoreCase(request.getParameter("code")));
		}
	}
}