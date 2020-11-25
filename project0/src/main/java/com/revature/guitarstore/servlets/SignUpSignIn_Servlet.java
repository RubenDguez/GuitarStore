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
import com.revature.guitarstore.users.LogInModel;
import com.revature.guitarstore.users.SignUserModel;
import com.revature.guitarstore.users.User;
import com.revature.guitarstore.users.UserDAO;
import com.revature.guitarstore.users.UserException;
import com.revature.guitarstore.users.UserLogin;

public class SignUpSignIn_Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(SignUpSignIn_Servlet.class);
	Gson gson = new Gson();

	public SignUpSignIn_Servlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String string[] = request.getRequestURI().split("/");
		String choice = string[3];

		switch (choice.toLowerCase()) {
		case "up":
			signIn(request, response);
			break;
		case "out":
			signOut(request, response);
			break;
		case "recover":
			recover(request, response);
			break;
		default:
			response.setContentType("application/json");
			response.getWriter().append(gson.toJson("400 Bad Request"));
			response.setStatus(400);
			logger.warn("400 Bad Request");
		}

	}

	private void signIn(HttpServletRequest req, HttpServletResponse res) throws IOException {

		HttpSession s = req.getSession();

		// no need to sign up if already have a session with an username
		if (s.getAttribute("username") == null) {

			BufferedReader reader = req.getReader();

			StringBuffer sb = new StringBuffer();
			String line;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			String json = sb.toString();
			SignUserModel sum = gson.fromJson(json, SignUserModel.class);

			if (sum != null) {

				try {

					User user = new User();
					user.setUsername(sum.getUsername());
					user.setEmail(sum.getEmail());
					user.setPassword(sum.getPassword());

					User newUser = UserDAO.inserUser(user);
					res.setContentType("application/json");
					res.getWriter().append(gson.toJson(newUser));
					res.setStatus(201);
					
					logger.debug("Action: Sign Up" + " user: " + s.getAttribute("username") + "Session id: " + s.getId());

				} catch (UserException e) {

					res.setContentType("application/json");
					res.getWriter().append(gson.toJson(e.getMessage()));
					res.setStatus(404);
					logger.error("404 Not Found" + e.toString());

				}

			} else {

				res.setContentType("application/json");
				res.getWriter().append(gson.toJson("400 Bad Request"));
				res.setStatus(400);
				logger.warn("400 Bad Request");

			}

		} else {

			res.setContentType("application/json");
			res.getWriter().append(
					gson.toJson("405 Method Not Allowed: User already signed in as " + s.getAttribute("username")));
			res.setStatus(405);
			logger.warn("405 Method Not Allowed: User already signed in as " + s.getAttribute("username"));

		}

	}

	private void signOut(HttpServletRequest req, HttpServletResponse res) throws IOException {

		HttpSession s = req.getSession();

		if ((s.getAttribute("id") != null && ((Integer) s.getAttribute("usertype") == 3))) {

			try {

				boolean b = UserDAO.deleteUser((Integer) s.getAttribute("id"));

				s.removeAttribute("id");
				s.removeAttribute("username");
				s.removeAttribute("email");
				s.removeAttribute("usertype");
				s.invalidate();
				s = req.getSession(false);

				res.setContentType("application/json");
				res.getWriter().append(gson.toJson("User opted out:" + b));
				res.setStatus(200);
				logger.debug("Action: opt-out" + " user: " + s.getAttribute("username") + "Session id: " + s.getId());

			} catch (UserException e) {

				res.setContentType("application/json");
				res.getWriter().append(gson.toJson(e.getMessage()));
				res.setStatus(404);
				logger.error("404 Not Found" + e.toString());

			}

		} else {

			res.setContentType("application/json");
			res.getWriter().append(gson.toJson("401 Unauthorized"));
			res.setStatus(401);
			logger.warn("401 Unauthorized");

		}

	}

	private void recover(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession s = req.getSession();

		// no need to recover account if already have a session with an username
		if (s.getAttribute("username") == null) {

			BufferedReader reader = req.getReader();

			StringBuffer sb = new StringBuffer();
			String line;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			String json = sb.toString();
			LogInModel lim = gson.fromJson(json, LogInModel.class);

			if (lim != null) {

				User user = UserLogin.getInactive(lim.getUsername(), lim.getPassword());

				if (user != null) {

					try {

						boolean b = UserDAO.reactivateUser(user.getUniqueID());

						if (b) {

							res.setContentType("application/json");
							res.getWriter().append(gson.toJson("user reactivated: " + b));
							res.setStatus(200);
							logger.debug("Action: Recover" + " user: " + s.getAttribute("username") + "Session id: " + s.getId());
							
						} else {

							res.setContentType("application/json");
							res.getWriter().append(gson.toJson("404 user coul not be reactivated"));
							res.setStatus(404);
							logger.warn("404 Not Found");

						}

					} catch (UserException e) {

						res.setContentType("application/json");
						res.getWriter().append(gson.toJson("404: " + e.toString()));
						res.setStatus(404);
						logger.error("404 Not Found" + e.toString());

					}
					
				} else {
					
					res.setContentType("application/json");
					res.getWriter().append(gson.toJson("404 Not Found"));
					res.setStatus(404);
					logger.warn("404 Not Found");
					
				}

			} else {

				res.setContentType("application/json");
				res.getWriter().append(gson.toJson("400 Bad Request"));
				res.setStatus(400);
				logger.warn("400 Bad Request");

			}

		} else {

			res.setContentType("application/json");
			res.getWriter().append(
					gson.toJson("405 Method Not Allowed: User already signed in as " + s.getAttribute("username")));
			res.setStatus(405);
			logger.warn("405 Method Not Allowed: User already signed in as " + s.getAttribute("username"));

		}

	}
}
