package com.tomson.mywebapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("userid");
		String password = request.getParameter("password");

		if (userId.equals("tomson") && password.equals("123456")) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userid", userId);
			out.println("login successfully");
		} else {
			out.println("login failed");
		}
	}
}
