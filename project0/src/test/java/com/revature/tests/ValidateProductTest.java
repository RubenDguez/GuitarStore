package com.revature.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.guitarstore.exceptions.GuitarStoreException;
import com.revature.guitarstore.model.Product;
import com.revature.guitarstore.product.ValidateProduct;

public class ValidateProductTest {

	private Product p;
	
	public void setUp() throws Exception {
		
		p = new Product(
					1,
					"Marting Electric Guitar 10254",
					"This product is made by Martin and which means all the good stuff that makes a good guitar is present in this product",
					100.00,
					1,1,1,1,1,1
				);
		
	}
	
	@Test
	public void isValid_PosID_Zero() throws GuitarStoreException {
		p.setPosID(0);
		assertFalse(ValidateProduct.isValid(p));
	}
	
	@Test(expected=GuitarStoreException.class)
	public void isValid_TitleLessThanTenChar() throws GuitarStoreException {
		p.setTitle("12345");
		ValidateProduct.isValid(p);
	}
}
