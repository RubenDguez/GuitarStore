package com.revature.guitarstore.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.guitarstore.users.User;
import com.revature.guitarstore.users.UserDAO;
import com.revature.guitarstore.users.UserException;

public class User_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Gson gson = new Gson();

	public User_Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession s = request.getSession();

		if ((s.getAttribute("username") != null) && ((Integer) s.getAttribute("usertype") == 1)) {

			String json = gson.toJson(UserDAO.getUsers());

			response.setContentType("application/json");
			response.getWriter().append(json);
			response.setStatus(200);

		} else {

			response.setContentType("application/json");
			response.getWriter().append(gson.toJson("401 Unauthorized"));
			response.setStatus(401);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession s = request.getSession();
		if ((s.getAttribute("username") != null) && ((Integer) s.getAttribute("usertype") == 1)) {

			BufferedReader reader = request.getReader();

			StringBuffer sb = new StringBuffer();
			String line;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			String json = sb.toString();

			User user = gson.fromJson(json, User.class);

			try {

				user = UserDAO.inserUser(user);
				response.setContentType("application/json");
				response.getWriter().append(gson.toJson(user));
				response.setStatus(201);

			} catch (UserException e) {
				response.setContentType("application/json");
				response.getWriter().append(gson.toJson(e.getMessage()));
				response.setStatus(404);
			}

		} else {

			response.setContentType("application/json");
			response.getWriter().append(gson.toJson("401 Unauthorized"));
			response.setStatus(401);

		}
	}
		
}
