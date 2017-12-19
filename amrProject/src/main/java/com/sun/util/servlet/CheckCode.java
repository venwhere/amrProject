package com.sun.util.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/CheckCode")
public class CheckCode extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String rand = (String) session.getAttribute("rand") ;
		if (rand == null || "".equals(rand)) {
			response.getWriter().print(false);
		} else {
			response.getWriter().print(rand.equalsIgnoreCase(request.getParameter("code")));
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}