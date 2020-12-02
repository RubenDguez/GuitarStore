package com.revature.tests;

import static org.junit.Assert.*;

// import org.junit.After; // this will run once after all tests
// import org.junit.AfterClass; // this will run after every test
// import org.junit.Before; // this will run once before all tests
import org.junit.BeforeClass; // this will run before every test
import org.junit.Test;

import com.revature.guitarstore.exceptions.GuitarStoreException;
import com.revature.guitarstore.model.Product;
import com.revature.guitarstore.product.ValidateProduct;

public class ValidateProductTest {

	private static Product p;
	
	@BeforeClass
	public static void setUp() {
		
		p = new Product(
					1,
					"Marting Electric Guitar 10254",
					"This product is made by Martin and which means all the good stuff that makes a good guitar is present in this product",
					100.00,
					1,1,1,1,1,1
				);
		
	}
	
	@Test
	public void isValid_WithProductPreviouslyLoadedInSetUp() throws GuitarStoreException {
		assertTrue(ValidateProduct.isValid(p));
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
	
	@Test(expected=GuitarStoreException.class)
	public void isValid_TitleMoreThan254Char() throws GuitarStoreException {
		p.setTitle("idvRZ1sUxRVyGJmpgaWpfpnWPEqRzibBVbS94pAUcfQN3Eizff1xHakBKPjuZqjYuWj0vGtGaooNkvbirGddUppWwE9QsNdAKy1hygdFg9R8g3MMl0lGZlMui0bQexR5MN3OtdPGbV0nt86y2WCKvEDpMYZoBLOArKnQzh8w1eu9RfdcdV7XZdNZX3kEFhDQ0yt3MSTOk4MMXPN2HAH9oz4g3PCOAJTD1e4kjU7LejmM3GAX0nuOHoPorugxvGf");
		ValidateProduct.isValid(p);
	}
	
	@Test(expected=GuitarStoreException.class)
	public void isValid_DescriptionEqualsNull() throws GuitarStoreException {
		p.setDescription(null);
		ValidateProduct.isValid(p);
	}
	
	@Test(expected=GuitarStoreException.class)
	public void isValid_DescriptionLengthLessThan10Char() throws GuitarStoreException {
		p.setDescription("1234");
		ValidateProduct.isValid(p);
	}
	
	@Test(expected=GuitarStoreException.class)
	public void isValid_PriceLessThanZero() throws GuitarStoreException {
		p.setPrice(-1);
		ValidateProduct.isValid(p);
	}
}
