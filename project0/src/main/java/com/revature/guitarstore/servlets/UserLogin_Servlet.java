package com.revature.guitarstore.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.revature.guitarstore.users.LogInModel;
import com.revature.guitarstore.users.User;
import com.revature.guitarstore.users.UserLogin;

public class UserLogin_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Gson gson = new Gson();

	public UserLogin_Servlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(600000);

		if (session.getAttribute("username") == null) {

			BufferedReader reader = request.getReader();
			StringBuffer sb = new StringBuffer();
			String line;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			String jsonString = sb.toString();

			try {

				LogInModel lim = gson.fromJson(jsonString, LogInModel.class);
				User user = UserLogin.getAccess(lim.getUsername(), lim.getPassword());

				if (user != null) {
					
					session.setAttribute("id", user.getUniqueID());
					session.setAttribute("username", user.getUsername());
					session.setAttribute("email", user.getEmail());
					session.setAttribute("usertype", user.getUserType_UID());

					response.setContentType("application/json");
					response.getWriter().append("session: " + session.getId() + "\n" + gson.toJson(user));

					response.setStatus(200);
					
				} else {

					response.setContentType("application/json");
					response.getWriter().append("404 Not Found");
					response.setStatus(404);

				}

			} catch (JsonProcessingException e) {

				response.setContentType("application/json");
				response.getWriter().append("404 Not Found");
				response.setStatus(404);

			}

		} else {

			response.setContentType("application/json");
			response.getWriter().append("405 Method Not Allowed");
			response.setStatus(405);

		}
	}
}
