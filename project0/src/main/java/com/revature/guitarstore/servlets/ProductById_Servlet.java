package com.revature.guitarstore.servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.revature.guitarstore.exceptions.GuitarStoreException;
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


}
