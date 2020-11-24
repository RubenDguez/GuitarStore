package com.revature.guitarstore.product;

import com.revature.guitarstore.DAO.BrandDAO;
import com.revature.guitarstore.DAO.CategoryDAO;
import com.revature.guitarstore.DAO.ConditionDAO;
import com.revature.guitarstore.DAO.DepartmentDAO;
import com.revature.guitarstore.DAO.PremiumGearDAO;
import com.revature.guitarstore.DAO.StyleDAO;
import com.revature.guitarstore.exceptions.GuitarStoreException;
import com.revature.guitarstore.model.Product;

public class ValidateProduct {

	public static boolean isValid(Product product) throws GuitarStoreException {
		
		// product POS ID rules
		if (product.getPosID() == 0) return false;
		
		// product title rules
		if (product.getTitle() == null) throw new GuitarStoreException("Product title cannot be null");
		if (product.getTitle().length() < 10) throw new GuitarStoreException("Product title cannot be less than 10 characters");
		if (product.getTitle().length() > 254) throw new GuitarStoreException("Product title cannot be greater than 254 charcaters");
		
		// product description rules
		if (product.getDescription() == null) throw new GuitarStoreException("Product description cannot be null");
		if (product.getDescription().length() < 10) throw new GuitarStoreException("Product description cannot be less than 10 characters");
		
		// price
		if (product.getPrice() < 0) throw new GuitarStoreException("Product price cannot have a negative value");
		

		final String FBACK = " UNIQUEID provided does not exists in Database";
		
		// validating provided Department ID
		DepartmentDAO ddao = new DepartmentDAO();
		if (!ddao.uniqueIdExists(product.getDepartment_UID())) throw new GuitarStoreException("Department" + FBACK);
		
		// validating provided Style ID
		StyleDAO sdao = new StyleDAO();
		if (!sdao.uniqueIdExists(product.getStyle_UID())) throw new GuitarStoreException("Style" + FBACK);
		
		
		// validating provided Category ID
		CategoryDAO cdao = new CategoryDAO();
		if (!cdao.uniqueIdExists(product.getCategory_UID())) throw new GuitarStoreException("Category" + FBACK);
		
		// validating provided Brand ID
		BrandDAO bdao = new BrandDAO();
		if (!bdao.uniqueIdExists(product.getBrand_UID())) throw new GuitarStoreException("Brand" + FBACK);
		
		// validating provided PremiumGear ID
		PremiumGearDAO pgdao = new PremiumGearDAO();
		if (!pgdao.uniqueIdExists(product.getPremiumGear_UID())) throw new GuitarStoreException("Premium Gear" + FBACK);
		
		// validating provided condition ID
		ConditionDAO codao = new ConditionDAO();
		if (!codao.uniqueIdExists(product.getCondition_UID())) throw new GuitarStoreException("Condition" + FBACK);
		
		return true;
	}
	
}
