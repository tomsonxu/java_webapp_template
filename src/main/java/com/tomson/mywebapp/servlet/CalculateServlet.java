package com.tomson.mywebapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tomson.mywebapp.service.CalculateService;

public class CalculateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CalculateService calService=new CalculateService();
		request.setAttribute("msg", String.valueOf(calService.add(1, 2)));
		request.getRequestDispatcher("/calculate.jsp").forward(request, response);
	}
}
