package com.revature.guitarstore.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.google.gson.Gson;
import com.revature.guitarstore.users.SignUserModel;
import com.revature.guitarstore.users.User;
import com.revature.guitarstore.users.UserDAO;
import com.revature.guitarstore.users.UserException;

public class SignUpSignIn extends HttpServlet {

	private static final long serialVersionUID = 1L;

	Gson gson = new Gson();

	public SignUpSignIn() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setStatus(200);
		response.setContentType("application/json");
		response.getWriter().append(gson.toJson("You shoud use a post request for the magic to happen"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession s = request.getSession();

		String string[] = request.getRequestURI().split("/");
		String choice = string[3];

		switch (choice) {
		case "in":
			signIn(request, response);
			break;
		case "out":
			signOut(request, response);
			break;
		case "on":
			break;
		default:

		}

	}

	private void signIn(HttpServletRequest req, HttpServletResponse res) throws IOException {

		HttpSession s = req.getSession();

		// no need to sign in if already have a session with username
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

				} catch (UserException e) {

					res.setContentType("application/json");
					res.getWriter().append(gson.toJson(e.getMessage()));
					res.setStatus(404);

				}

			} else {

				res.setContentType("application/json");
				res.getWriter().append(gson.toJson("400 Bad Request"));
				res.setStatus(400);

			}

		} else {

			res.setContentType("application/json");
			res.getWriter().append(
					gson.toJson("405 Method Not Allowed: User already signed in as " + s.getAttribute("username")));
			res.setStatus(405);

		}

	}

	private void signOut(HttpServletRequest req, HttpServletResponse res) throws IOException {

		HttpSession s = req.getSession();
		
		if ((s.getAttribute("id") != null && ((Integer)s.getAttribute("usertype") == 3))) {
			
			
			
		} else {
			
			res.setContentType("application/json");
			res.getWriter().append(gson.toJson("401 Unauthorized"));
			res.setStatus(401);
			
		}
		
	}
	
}
