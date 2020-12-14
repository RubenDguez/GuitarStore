package com.revature.guitarstore.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Home_Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.setContentType("text/html");
		String message = "Hello World";
		request.setAttribute("message", message);
		request.getRequestDispatcher("/WEB-INF/Homepage.jsp").forward(request, response);
		response.setStatus(200);

	}

}
