package com.tomson.mywebapp.servlet;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

public class LoginServletTests {
	private LoginServlet loginServlet;
	private HttpServletRequest mockRequest;
	private HttpServletResponse mockResponse;

	@Before
	public void setup() {
		loginServlet = new LoginServlet();
		//create mock objects
		mockRequest = createMock(HttpServletRequest.class);
		mockResponse = createMock(HttpServletResponse.class);
	}

	@Test
	public void testLoginSuccess() throws Exception {
		//expect mock response
		mockResponse.setContentType("text/html;charset=utf-8");
		mockRequest.setCharacterEncoding("utf-8");

		// important 3 lines!!! var "output" is used to check response output!!
		StringWriter output = new StringWriter();
		PrintWriter contentWriter = new PrintWriter(output);
		expect(mockResponse.getWriter()).andReturn(contentWriter);

		//expect request get parameter
		expect(mockRequest.getParameter("userid")).andReturn("tomson");
		expect(mockRequest.getParameter("password")).andReturn("123456");
		
		//mock httpsession and expect session operation
		HttpSession mockSession=createMock(HttpSession.class);
		expect(mockRequest.getSession(true)).andReturn(mockSession);		
		mockSession.setAttribute("userid", "tomson");
		
		//replay state is ready
		replay(mockRequest);
		replay(mockResponse);
		replay(mockSession);
		
		loginServlet.doPost(mockRequest, mockResponse);
		assertEquals("login successfully\n", output.toString()); 
		
		verify(mockRequest);
		verify(mockResponse);
		verify(mockSession);
	}

	@Test
	public void testLoginFail() throws Exception {
		//expect mock response
		mockResponse.setContentType("text/html;charset=utf-8");
		mockRequest.setCharacterEncoding("utf-8");

		// important 3 lines!!! var "output" is used to check response output!!
		StringWriter output = new StringWriter();
		PrintWriter contentWriter = new PrintWriter(output);
		expect(mockResponse.getWriter()).andReturn(contentWriter);

		//expect request get parameter
		expect(mockRequest.getParameter("userid")).andReturn("wrong id");
		expect(mockRequest.getParameter("password")).andReturn("wrong pwd");		
		
		//replay state is ready
		replay(mockRequest);
		replay(mockResponse);
		
		loginServlet.doPost(mockRequest, mockResponse);
		assertEquals("login failed\n", output.toString());
		
		verify(mockRequest);
		verify(mockResponse);
	}
}
