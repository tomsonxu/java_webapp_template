package com.tomson.mywebapp.servlet;

import static org.easymock.EasyMock.*;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

public class MainPageServletTests {
	private MainPageServlet mainPageServlet;
	private HttpServletRequest mockRequest;
	private HttpServletResponse mockResponse;

	@Before
	public void setup() {
		mainPageServlet = new MainPageServlet();
		//create mock objects
		mockRequest = createMock(HttpServletRequest.class);
		mockResponse = createMock(HttpServletResponse.class);
	}

	@Test
	public void testDoGetWithNullSession() throws Exception {
		//expect redirect operation
		expect(mockRequest.getSession()).andReturn(null);
		mockResponse.sendRedirect("error.jsp");
		
		//replay state
		replay(mockRequest);
		replay(mockResponse);
		
		mainPageServlet.doGet(mockRequest, mockResponse); //tested method
		
		verify(mockRequest);
		verify(mockResponse);
	}

	@Test
	public void testDoGetWithNullAttributeInSession() throws Exception {
		//mock httpsession and expect session operation
		HttpSession mockSession=createMock(HttpSession.class);
		expect(mockRequest.getSession()).andReturn(mockSession);
		expect(mockSession.getAttribute("userid")).andReturn(null);

		//expect redirect operation
		mockResponse.sendRedirect("error.jsp");
		
		//replay state
		replay(mockRequest);
		replay(mockResponse);
		replay(mockSession);
		
		mainPageServlet.doGet(mockRequest, mockResponse); //tested method
		
		verify(mockRequest);
		verify(mockResponse);
		verify(mockSession);
	}
	
	@Test
	public void testDoGetWithValidLoginSession() throws Exception {
		//mock httpsession and expect session operation
		HttpSession mockSession=createMock(HttpSession.class);
		expect(mockRequest.getSession()).andReturn(mockSession);
		expect(mockSession.getAttribute("userid")).andReturn("valid user id");
		
		//if use following script, error occurs
		//mockRequest.setAttribute("items", isA(ArrayList.class));
		//because you need to use no matcher at all or a matcher for every single param. for example
		//wrong:  foo(5, eq(6))
		//right:  foo(eq(5), eq(6));
		//also right: foo(5,6)
		mockRequest.setAttribute(eq("items"), isA(ArrayList.class));  //pay attention how to expect list object

		//expect forward to jsp operation
		RequestDispatcher mockRequestDispatcher=createMock(RequestDispatcher.class);
		expect(mockRequest.getRequestDispatcher("/main.jsp")).andReturn(mockRequestDispatcher);
		mockRequestDispatcher.forward(mockRequest, mockResponse);
		
		//replay state
		replay(mockRequest);
		replay(mockResponse);
		replay(mockSession);
		
		mainPageServlet.doGet(mockRequest, mockResponse); //tested method
		
		verify(mockRequest);
		verify(mockResponse);
		verify(mockSession);		
	}
	
}
