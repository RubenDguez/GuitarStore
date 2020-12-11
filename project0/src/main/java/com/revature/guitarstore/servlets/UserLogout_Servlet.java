package com.revature.guitarstore.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserLogout_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(UserLogout_Servlet.class);
       

    public UserLogout_Servlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		logger.info("User logged out: " + session.getAttribute("username"));
		
		session.removeAttribute("");
		
		session.removeAttribute("id");
		session.removeAttribute("username");
		session.removeAttribute("email");
		session.removeAttribute("usertype");
		
		session.invalidate();
		session = request.getSession(false);
		response.getWriter().println("Logged out: " + true + "\nSession: " + session);
		
	}

}
