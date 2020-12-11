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
import com.revature.guitarstore.exceptions.GuitarStoreException;
import com.revature.guitarstore.model.Product;
import com.revature.guitarstore.product.ProductDAO;
import com.revature.guitarstore.product.ProductTemplate;

public class ProductById_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(ProductById_Servlet.class);
	Gson gson = new Gson();

	public ProductById_Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String string[] = request.getRequestURI().split("/");
		if (string.length > 3) {

			int productId = Integer.parseInt(string[3]);
			ProductDAO pdao = new ProductDAO();

			if (pdao.uniqueIdExists(productId)) {

				try {

					ProductTemplate pt = new ProductTemplate(productId);
					response.setContentType("application/json");
					response.setStatus(200);
					response.getWriter().append(gson.toJson(pt));

				} catch (GuitarStoreException e) {

					response.setContentType("application/json");
					response.setStatus(404);
					response.getWriter().append("404 Not Found");
					logger.error("404 Not Found" + e.toString());

				}

			} else {

				response.setContentType("application/json");
				response.setStatus(404);
				response.getWriter().append("404 Not Found");
				logger.warn("404 Not Found");

			}

		} else {
			response.setContentType("application/json");
			response.setStatus(400);
			response.getWriter().append("400 Bad Request");
			logger.warn("400 Bad Request");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// verifies that at least an user is logged in
		if (session.getAttribute("id") != null) {

			// verifies that user must be an employee or administrator
			if ((Integer) session.getAttribute("id") < 3) {

				ProductDAO pdao = new ProductDAO();

				// gets the ID from URI
				String string[] = request.getRequestURI().split("/");
				
				// makes sure we can get an id from URI
				if (string.length > 4) {
					
					int id = Integer.parseInt(string[4]);
					
					try {
						response.setContentType("application/json");
						response.setStatus(200);
						response.getWriter().append(gson.toJson(pdao.delete(id)));
						logger.debug("Action: Delete Product" + " user: " + session.getAttribute("username"));

					} catch (GuitarStoreException e) {
						response.setContentType("application/json");
						response.setStatus(400);
						response.getWriter().append("400 Bad Request: " + e.toString());
						logger.error("400 Bad Request" + e.toString());
					}
				} else {
					response.setContentType("application/json");
					response.setStatus(400);
					response.getWriter().append("400 Bad Request");
					logger.error("400 Bad Request");
				}
			} else {
				response.setContentType("application/json");
				response.setStatus(401);
				response.getWriter().append("401 Unauthorized");
				logger.error("401 Unauthorized");
			}

		} else {
			response.setContentType("application/json");
			response.setStatus(401);
			response.getWriter().append("401 Unauthorized");
			logger.error("401 Unauthorized");
		}
	}

}
