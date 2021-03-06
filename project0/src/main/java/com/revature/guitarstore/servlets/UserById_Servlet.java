package com.revature.guitarstore.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.revature.guitarstore.users.User;
import com.revature.guitarstore.users.UserDAO;
import com.revature.guitarstore.users.UserException;

public class UserById_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(UserById_Servlet.class);
	Gson gson = new Gson();

	public UserById_Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession s = request.getSession();

		String string[] = request.getRequestURI().split("/");
		if (string.length > 3) {
			
			if ((s.getAttribute("username") != null) && ((Integer) s.getAttribute("usertype") == 1)) {

				int id = Integer.parseInt(string[3]);
				
				try {

					User user = UserDAO.getUserById(id);
					response.setContentType("application/json");
					response.getWriter().append(gson.toJson(user));
					response.setStatus(201);
					logger.debug("Action: Get User By Id" + " user: " + s.getAttribute("username") + "Session id: " + s.getId());

				} catch (UserException e) {
					response.setContentType("application/json");
					response.getWriter().append(gson.toJson(e.getMessage()));
					response.setStatus(404);
					logger.error("404 Not Found" + e.toString());
				}

			} else {

				response.setContentType("application/json");
				response.getWriter().append(gson.toJson("401 Unauthorized"));
				response.setStatus(401);
				logger.warn("401 Unauthorized");

			}
		} else {
			response.setContentType("application/json");
			response.getWriter().append(gson.toJson("401 Unauthorized"));
			response.setStatus(401);
			logger.warn("401 Unauthorized");
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession s = request.getSession();

		String string[] = request.getRequestURI().split("/");

		if (string.length > 3) {

			if ((s.getAttribute("username") != null) && ((Integer) s.getAttribute("usertype") == 1)) {
				
				int id = Integer.parseInt(string[3]);
				
				try {

					BufferedReader reader = request.getReader();

					StringBuffer sb = new StringBuffer();
					String line;

					while ((line = reader.readLine()) != null) {
						sb.append(line);
					}

					String jsonString = sb.toString();

					User updatedUser = gson.fromJson(jsonString, User.class);

					User user = UserDAO.updateUser(id, updatedUser);
					response.setContentType("application/json");
					response.getWriter().append(gson.toJson(user));
					response.setStatus(201);
					logger.debug("Action: Update User" + " user: " + s.getAttribute("username") + "Session id: " + s.getId());

				} catch (UserException e) {
					response.setContentType("application/json");
					response.getWriter().append(gson.toJson(e.getMessage()));
					response.setStatus(404);
					logger.error("404 Not Found" + e.toString());
				}

			} else {

				response.setContentType("application/json");
				response.getWriter().append(gson.toJson("401 Unauthorized"));
				response.setStatus(401);
				logger.warn("401 Unauthorized");

			}
		} else {
			response.setContentType("application/json");
			response.getWriter().append(gson.toJson("404 Not Found"));
			response.setStatus(404);
			logger.warn("404 Not Found");
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession s = request.getSession();

		String string[] = request.getRequestURI().split("/");
		
		if (string.length > 3) {

			if ((s.getAttribute("username") != null) && ((Integer) s.getAttribute("usertype") == 1)) {

				int id = Integer.parseInt(string[3]);
				
				try {

					boolean b = UserDAO.deleteUser(id);
					response.setContentType("application/json");
					response.getWriter().append(gson.toJson(b));
					response.setStatus(201);
					logger.debug("Action: Delete User" + " user: " + s.getAttribute("username") + "Session id: " + s.getId());

				} catch (UserException e) {
					response.setContentType("application/json");
					response.getWriter().append(gson.toJson(e.getMessage()));
					response.setStatus(404);
					logger.error("404 Not Found" + e.toString());
				}

			} else {

				response.setContentType("application/json");
				response.getWriter().append(gson.toJson("401 Unauthorized"));
				response.setStatus(401);
				logger.warn("401 Unauthorized");

			}
		} else {
			response.setContentType("application/json");
			response.getWriter().append("404 Not Found");
			response.setStatus(404);
			logger.warn("404 Not Found");
		}
	}

}
