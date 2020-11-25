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

public class Product_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LogManager.getLogger(Product_Servlet.class);
	
	Gson gson = new Gson();

	public Product_Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO pdao = new ProductDAO();
		try {

			response.setContentType("application/json");
			response.getWriter().append(gson.toJson(pdao.getAllActiveProducts()));
			response.setStatus(200);

		} catch (GuitarStoreException e) {

			response.setContentType("application/json");
			response.getWriter().append(gson.toJson("404 Not Found"));
			response.setStatus(404);
			logger.error("404 Not Found" + e.toString());

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (session.getAttribute("id") != null) {

			if ((Integer) session.getAttribute("id") < 3) {

				BufferedReader reader = request.getReader();

				StringBuffer sb = new StringBuffer();
				String line;

				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

				String jsonString = sb.toString();

				Product p = gson.fromJson(jsonString, Product.class);
				ProductDAO pdao = new ProductDAO();

				try {
					response.setContentType("application/json");
					response.setStatus(200);
					response.getWriter().append(gson.toJson(pdao.insert(p)));
					logger.debug("Action: Insert User" + " user: " + session.getAttribute("username") + "Session id: " + session.getId());

				} catch (GuitarStoreException e) {

					response.setContentType("application/json");
					response.setStatus(400);
					response.getWriter().append("400 Bad Request: " + e.toString());
					logger.error("400 Bad Request" + e.toString());

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
