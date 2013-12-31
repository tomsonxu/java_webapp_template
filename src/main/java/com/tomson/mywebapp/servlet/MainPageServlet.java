package com.tomson.mywebapp.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(isLogin(request)){
			ArrayList<String> items=new ArrayList<String>();
			items.add("item 1");
			items.add("item 2");
			request.setAttribute("items", items);
			request.getRequestDispatcher("/main.jsp").forward(request, response);			
		} else {
			response.sendRedirect("error.jsp");
		}
	}
	
	private boolean isLogin(HttpServletRequest request){  
        HttpSession session = request.getSession();  
        if(session==null)  
            return false;
        
        if(session.getAttribute("userid") == null)  
            return false; 
        
        return true;  
    }  	
}
