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


public class ProductByDepartment_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(ProductByDepartment_Servlet.class);
	Gson gson = new Gson();

    public ProductByDepartment_Servlet() {
        super();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String string[] = request.getRequestURI().split("/");
		if (string.length > 4) {
			int id = Integer.parseInt(string[4]);
			ProductDAO pdao = new ProductDAO();
			
			try {
				
				response.setContentType("application/json");
				response.setStatus(200);
				response.getWriter().append(gson.toJson(pdao.getAllActiveProductsByDepartment(id)));
				
			} catch (GuitarStoreException e) {
				response.setContentType("application/json");
				response.setStatus(404);
				response.getWriter().append("404 Not Found");
				logger.error("404 Not Found" + e.toString());
			}
			 
			
		}	
	}

}
